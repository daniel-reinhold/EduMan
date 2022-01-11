package com.eduman.ui.fragments.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.ui.adapters.recyclerview.LibrariesAdapter

class LibrariesFragment : EduManFragment("LibrariesFragment") {

    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_libraries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        recyclerView = activity?.findViewById(R.id.fragmentLibrariesRecyclerView)
        recyclerView?.adapter = LibrariesAdapter(object : LibrariesAdapter.LibrariesAdapterCallback {
            override fun onOpenLinkRequested(link: Uri) {
                val browserIntent = Intent(Intent.ACTION_VIEW, link)

                try {
                    activity?.startActivity(browserIntent)
                } catch (e: ActivityNotFoundException) {
                    Log.e(getLogTag(), "No activity for viewing web pages found")
                }
            }

        })
    }

}