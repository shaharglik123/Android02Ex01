package com.example.android02ex01.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android02ex01.Data.DataSource
import com.example.android02ex01.R
import com.example.android02ex01.databinding.FragmentCurrentCelebBinding
import com.example.android02ex01.models.Celeb
import com.google.android.material.transition.MaterialContainerTransform

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CurrentCelebFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CurrentCelebFragment : Fragment() {
//    TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    private val args: CurrentCelebFragmentArgs by navArgs()

    private val postId: Long by lazy(LazyThreadSafetyMode.NONE) { args.celebPost }

    private lateinit var binding: FragmentCurrentCelebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_home_fragment
            duration = resources.getInteger((R.integer.reply_motion_duration_large)).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_current_celeb, container, false)

        binding = FragmentCurrentCelebBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationBackIcon.setOnClickListener {
            findNavController().navigateUp()
        }
        initView()
    }


    private fun initView() {
        val celebPost = DataSource.createDataSet()[postId.toInt()]
        if (celebPost != null) {

            binding.run {
                celebTitle.text = celebPost.name
                celebBody.text = celebPost.description
                celebAuthor.text = celebPost.username
            }
            //init image
            setImageGlide(celebPost)
        }
    }
        private fun setImageGlide(blogCeleb: Celeb) {
            //default properties to Glide  - what to display if there is an error or the image cannot be displayed
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background)

            Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(blogCeleb.image)
                .into(binding.celebImage)

            Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(blogCeleb.image2)
                .into(binding.celebImage2)
        }





//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment CurrentCelebFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            CurrentCelebFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}