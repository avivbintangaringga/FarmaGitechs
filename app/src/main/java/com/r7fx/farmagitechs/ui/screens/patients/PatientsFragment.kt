package com.r7fx.farmagitechs.ui.screens.patients

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.r7fx.farmagitechs.R
import com.r7fx.farmagitechs.databinding.FragmentPatientsBinding
import com.r7fx.farmagitechs.databinding.ItemPatientBinding
import com.r7fx.farmagitechs.domain.model.Gender
import com.r7fx.farmagitechs.domain.model.Patient
import com.r7fx.farmagitechs.ui.adapter.GeneralAdapter
import com.r7fx.farmagitechs.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientsFragment : BaseFragment(R.layout.fragment_patients) {
    private val binding: FragmentPatientsBinding by viewBinding()
    private val viewModel: PatientsViewModel by viewModels()

    private val adapter by lazy {
        GeneralAdapter<Patient, ItemPatientBinding>(
            ItemPatientBinding::inflate
        ).onItemClick {
            findNavController().navigate(
                PatientsFragmentDirections.actionPatientsFragmentToDetailsFragment(it.id)
            )
        }.onBind { view, data ->
            with(view) {
                txtName.text = data.name
                txtAddress.text = data.address
                txtNo.text = data.id

                val gender = if (data.gender == Gender.MALE) "male" else "female"

                Glide.with(root)
                    .load("https://xsgames.co/randomusers/avatar.php?g=$gender&id=${data.id}")
                    .into(imgProfile)
            }
        }.build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerView.adapter = adapter

            with(viewModel) {
                onLoading.onResult {
                    loading.root.isVisible = it
                }

                onFetchResult.onResult {
                    adapter.submitList(it)
                }

                errorMessage.onResult {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }

                edtSearch.setOnEditorActionListener { v, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        search(edtSearch.text.toString())
                        return@setOnEditorActionListener true
                    }

                    return@setOnEditorActionListener false
                }

                recyclerView.addOnScrollListener(object : OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        (recyclerView.layoutManager as LinearLayoutManager).let {
                            if (it.findLastVisibleItemPosition() == adapter.itemCount - 1 && !isLast) {
                                Log.d("SDKSDK", "FETCH")
                                fetchNext()
                            }
                        }
                    }
                })
            }
        }
    }
}