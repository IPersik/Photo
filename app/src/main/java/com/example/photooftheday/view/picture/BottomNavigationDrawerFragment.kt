package com.example.photooftheday.view.picture

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.photooftheday.R
import com.example.photooftheday.databinding.BottomNavigationLayoutBinding
import com.example.photooftheday.view.animations.AnimationsActivity
import com.example.photooftheday.view.constraint.ConstraintFragment
import com.example.photooftheday.view.coordinator.CoordinatorFragment
import com.example.photooftheday.view.motion.MotionFragment
import com.example.photooftheday.view.recycler.RecyclerActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: BottomNavigationLayoutBinding? = null
    val binding: BottomNavigationLayoutBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.navigation_one -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ConstraintFragment.newInstance())
                        .addToBackStack("").commit()
                }
                R.id.navigation_two -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, CoordinatorFragment.newInstance())
                        .addToBackStack("").commit()
                }
                R.id.navigation_third -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MotionFragment.newInstance()).addToBackStack("")
                        .commit()
                }
                R.id.navigation_four -> {
                   startActivity(Intent(requireActivity(), AnimationsActivity::class.java))
                }
                R.id.navigation_five -> {
                    startActivity(Intent(requireActivity(), RecyclerActivity::class.java))
                }
            }
            dismiss()
            true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }
}