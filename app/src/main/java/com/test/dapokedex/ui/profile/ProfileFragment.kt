package com.test.dapokedex.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.test.dapokedex.R
import com.test.dapokedex.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            profileButton.setOnClickListener {

                if (imageUrl.editText?.text?.length == 0 && imageName.editText?.text?.length != 0){
                    cardImage.visibility = View.GONE
                    cardName.visibility = View.VISIBLE
                    var text = imageName.editText?.text
                    var initials = text?.split(" ")

                    cardName.setText(
                        initials?.get(0)?.get(0)?.uppercase() +
                                initials?.get(1)?.get(0)?.uppercase()
                    )
                } else if (imageUrl.editText?.text?.length != 0) {
                    cardImage.visibility = View.VISIBLE
                    cardName.visibility = View.GONE

                    Glide.with(this@ProfileFragment)
                        .load(imageUrl.editText?.text)
                        .placeholder(R.drawable.avatar002)
                        .dontAnimate()
                        .into(cardImage)
                }
            }
        }
    }

    companion object {
        fun newInstance(): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}