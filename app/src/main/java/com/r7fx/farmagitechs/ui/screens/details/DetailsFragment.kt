package com.r7fx.farmagitechs.ui.screens.details

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.r7fx.farmagitechs.R
import com.r7fx.farmagitechs.common.utils.Preferences
import com.r7fx.farmagitechs.databinding.FragmentDetailsBinding
import com.r7fx.farmagitechs.databinding.ItemDocumentBinding
import com.r7fx.farmagitechs.domain.model.Document
import com.r7fx.farmagitechs.domain.model.Gender
import com.r7fx.farmagitechs.ui.adapter.GeneralAdapter
import com.r7fx.farmagitechs.ui.base.BaseFragment
import com.rajat.pdfviewer.PdfViewerActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : BaseFragment(R.layout.fragment_details) {
    @Inject
    lateinit var prefs: Preferences
    private val binding: FragmentDetailsBinding by viewBinding()
    private val viewModel: DetailsViewModel by viewModels()
    private val adapter by lazy {
        GeneralAdapter<Document, ItemDocumentBinding>(
            ItemDocumentBinding::inflate
        ).onBind { view, data ->
            with(view) {
                txtName.text = data.title
                txtDate.text = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault()).format(data.date)

                val glideUrl = GlideUrl(
                    data.thumbnailUrl,
                    LazyHeaders.Builder()
                        .addHeader("Authorization", "Bearer ${prefs.token}")
                        .build()
                )

                Glide.with(root)
                    .load(glideUrl)
                    .into(imgThumbnail)
            }
        }.onItemClick {
            viewModel.openDocument(it)
        }.build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerView.adapter = adapter

            with(viewModel) {
                onFetchDetailsResult.onResult {
                    txtNama.text = it.name
                    txtNo.text = it.id
                    txtGender.text = if (it.gender == Gender.MALE) "Laki-laki" else "Perempuan"
                    txtDate.text = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(it.dateOfBirth)
                    txtAddress.text = it.address
                    val gender = if (it.gender == Gender.MALE) "male" else "female"
                    Glide.with(root)
                        .load("https://xsgames.co/randomusers/avatar.php?g=$gender&id=${it.id}")
                        .into(imgProfile)
                }

                onFetchDocumentsResult.onResult {
                    adapter.submitList(it)
                }

                errorMessage.onResult {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }

                onLoading.onResult {
                    loading.root.isVisible = it
                }

                onViewDocumentResult.onResult {
                    startActivity(
                        PdfViewerActivity.launchPdfFromPath(
                            requireContext(),
                            it.second,
                            it.first.title,
                            enableDownload = false,
                            directoryName = ""
                        )
                    )
                }

                edtSearch.setOnEditorActionListener { v, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        search(edtSearch.text.toString())
                        return@setOnEditorActionListener true
                    }

                    return@setOnEditorActionListener false
                }
            }
        }
    }
}