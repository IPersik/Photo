package com.example.photooftheday.view.picture


import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.QuoteSpan
import android.text.style.TypefaceSpan
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.provider.FontRequest
import androidx.core.provider.FontsContractCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.photooftheday.R
import com.example.photooftheday.databinding.FragmentMainStartBinding
import com.example.photooftheday.view.MainActivity
import com.example.photooftheday.view.api.ApiActivity
import com.example.photooftheday.view.api.ApiBottomActivity
import com.example.photooftheday.view.chips.SettingsFragment
import com.example.photooftheday.viewvodel.PictureOfTheDayState
import com.example.photooftheday.viewvodel.PictureOfTheDayViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.text.SimpleDateFormat
import java.util.*

class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentMainStartBinding? = null
    val binding: FragmentMainStartBinding
    get() {
        return _binding!!
    }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendServerRequest()

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }

        val behavior = BottomSheetBehavior.from(binding.includeBottomSheet.bottomSheetContainer)
        behavior.state = BottomSheetBehavior.STATE_HIDDEN


        behavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("mylogs", "$slideOffset slideOffset")
            }
        })

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yestrday -> {
                    viewModel.sendServerRequest(takeDate(-1))
                }
                R.id.today -> {
                    viewModel.sendServerRequest()
                }
            }
        }

        setBottomAppBar()

    }

    private fun takeDate(count: Int): String {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, count)
        val format1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format1.timeZone = TimeZone.getTimeZone("EST")
        return format1.format(currentDate.time)
    }


    private fun renderData(state: PictureOfTheDayState) {
        when (state) {
            is PictureOfTheDayState.Error -> {
                state.error.message
            }
            is PictureOfTheDayState.Loading -> {
                binding.imageView.load(R.drawable.ic_no_photo_vector)
            }
            is PictureOfTheDayState.Success -> {
                val pictureOfTheDayResponseData = state.pictureOfTheDayResponseData
                val url = pictureOfTheDayResponseData.url
                binding.imageView.load(url) {
                    lifecycle(this@PictureOfTheDayFragment)
                    error(R.drawable.ic_load_error_vector)
                    placeholder(R.drawable.ic_no_photo_vector)
                }
                pictureOfTheDayResponseData.explanation?.let {

                    initSpans(it)
                }
            }
        }
    }

    private fun initSpans(text:String) {
        val  r = (1..9999).random()
        Log.d("mylogs","initSpans ${r}")
        val requestCallback = FontRequest(
            "com.google.android.gms.fonts", "com.google.android.gms",
            "Aguafina Script", R.array.com_google_android_gms_fonts_certs
        )
        val callback = object : FontsContractCompat.FontRequestCallback(){
            override fun onTypefaceRetrieved(typeface: Typeface?) {
                Log.d("mylogs","onTypefaceRetrieved ${r}")
                typeface?.let {
                    Log.d("mylogs","let ${r}")
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        val spannableStart = SpannableStringBuilder(text)
                        binding.textView.setText(spannableStart, TextView.BufferType.EDITABLE)
                        val spannable = binding.textView.text as SpannableStringBuilder
                        spannable.setSpan(
                            ForegroundColorSpan(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.colorAccent
                                )
                            ),
                            0, 10, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                        )
                        spannable.insert(0, "1")
                        spannable.insert(10, "\n")
                        spannable.insert(20, "\n")

                        val q: QuoteSpan
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            q = QuoteSpan(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.colorAccent
                                ),
                                20,
                                40
                            )
                        } else {
                            q = QuoteSpan(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.colorAccent
                                )
                            )
                        }
                        spannable.setSpan(
                            q, 0, 20, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                        )
                        val qq = QuoteSpan(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.colorAccent
                            )
                        )
                        spannable.setSpan(
                            qq, 10, 19, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                        )


                        spannable.setSpan(TypefaceSpan(it),
                            0,50,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                        //spannable.insert(0, "1")
                    }
                }
            }

            override fun onTypefaceRequestFailed(reason: Int) {
                super.onTypefaceRequestFailed(reason)
                Log.d("mylogs","onTypefaceRequestFailed ${(1..9999).random()}")
            }
        }
        val handler = Handler(Looper.getMainLooper())
        FontsContractCompat.requestFont(requireContext(),requestCallback,callback,handler)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(): PictureOfTheDayFragment {
            return PictureOfTheDayFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.api_activity -> {
                startActivity(Intent(requireContext(), ApiActivity::class.java))
            }
            R.id.api_bottom_activity -> {
                startActivity(Intent(requireContext(), ApiBottomActivity::class.java))
            }
            R.id.app_bar_settings -> requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    SettingsFragment.newInstance()
                ).commit()
            android.R.id.home -> BottomNavigationDrawerFragment().show(
                requireActivity().supportFragmentManager,
                ""
            )
        }
        return super.onOptionsItemSelected(item)
    }

    private var isMain = true
    private fun setBottomAppBar() {
        val context = activity as MainActivity
        context.setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)


        binding.fab.setOnClickListener {
            if (isMain) {
                isMain = false
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                isMain = true
                binding.bottomAppBar.navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_hamburger_menu_bottom_bar)
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d(TAG, "Fragment back pressed invoked")
                    if (isMain) {
                        System.exit(0)
                    } else {
                        isMain = true
                        binding.bottomAppBar.navigationIcon = ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_hamburger_menu_bottom_bar
                        )
                        binding.bottomAppBar.fabAlignmentMode =
                            BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                        binding.fab.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_plus_fab
                            )
                        )
                        binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
                    }
                }
            }
            )
    }

}