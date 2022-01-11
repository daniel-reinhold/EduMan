package com.eduman.ui.adapters.recyclerview

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.Constants.Companion.USED_LIBRARIES
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class LibrariesAdapter(
    private val callback: LibrariesAdapterCallback
) : RecyclerView.Adapter<LibrariesAdapter.AdapterViewHolder>() {

    interface LibrariesAdapterCallback {
        fun onOpenLinkRequested(link: Uri)
    }

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val context: Context = view.context
        val card: MaterialCardView = view as MaterialCardView
        val textViewTitle: MaterialTextView = view.findViewById(R.id.rviLibraryTextViewTitle)
        val textViewLink: MaterialTextView = view.findViewById(R.id.rviLibraryTextViewLink)
        val imageViewLogo: ImageView = view.findViewById(R.id.rviLibraryImageViewLogo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rvi_library,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val library = USED_LIBRARIES[holder.adapterPosition]

        holder.textViewTitle.text = library.title
        holder.textViewLink.text = library.displayedUrl
        holder.imageViewLogo.setImageDrawable(
            ContextCompat.getDrawable(holder.context, library.logo)
        )

        holder.card.setOnClickListener {
            callback.onOpenLinkRequested(Uri.parse(library.url))
        }
    }

    override fun getItemCount() = USED_LIBRARIES.size

}