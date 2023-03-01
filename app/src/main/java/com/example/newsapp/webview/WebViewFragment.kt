package com.example.newsapp.webview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import com.example.newsapp.databinding.FragmentWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebViewBinding

    private val viewModel by viewModels<WebViewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        val view = binding.root
        webViewSetup()
        return view

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup() {
        binding.webView.webViewClient = WebViewClient()
        binding.webView.apply {
            viewModel.articleUrl.value?.let { loadUrl(it) }
        }
    }
}