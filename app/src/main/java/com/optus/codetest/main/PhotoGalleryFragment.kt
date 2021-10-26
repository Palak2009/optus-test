package com.optus.codetest.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.optus.codetest.R
import com.optus.codetest.databinding.FragmentAlbumBinding
import com.optus.codetest.models.Photo
import com.optus.codetest.utils.Constants
import com.optus.codetest.utils.DLog
import com.optus.codetest.utils.Injector
import com.optus.codetest.utils.showSnackbar
import com.optus.codetest.viewmodels.GalleryViewModel
import kotlinx.android.synthetic.main.fragment_album.*
import java.net.UnknownHostException

class PhotoGalleryFragment : Fragment(), PhotoGalleryRecylerAdapter.OnAlbumItemClickListener {

    private var userId = -1
    private lateinit var recyclerAdapter: PhotoGalleryRecylerAdapter
    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userId = arguments?.getInt(Constants.INTENT_EXTRA_USER_ID) ?: -1
        DLog.d("userId $userId")

        initPhotosViewModel()
        recyclerAdapter = PhotoGalleryRecylerAdapter(activity, this)
    }

    private fun initPhotosViewModel() {
        galleryViewModel = androidx.lifecycle.ViewModelProviders.of(
            this,
            Injector.provideMyViewModelFactory(activity!!))
            .get(GalleryViewModel::class.java)

        galleryViewModel.photos.observe(this, Observer {
            it?.let {
                if (recyclerAdapter.itemCount == 0) {
                    DLog.d("photos size: ${it.size}")
                    recyclerAdapter.submitList(it)
                }
            }
        })

        galleryViewModel.errors.observe(this, Observer {
            it?.let {
                if (it is UnknownHostException) {
                    albumFragmentContainer.showSnackbar(getString(R.string.snackbar_message_no_internet))
                } else {
                    albumFragmentContainer.showSnackbar(getString(R.string.snackbar_message_generic_error))
                }
                DLog.d("error: $it")
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentAlbumBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_album, container, false)
        binding.apply {
            albumAdapter = recyclerAdapter
            viewModel = galleryViewModel
            lifecycleOwner = this@PhotoGalleryFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        galleryViewModel.getAlbumWithUserId(userId)
    }

    override fun onAlbumItemClicked(photo: Photo) {
        (activity as MainActivity).goToPhotoFragment(photo)
    }

}
