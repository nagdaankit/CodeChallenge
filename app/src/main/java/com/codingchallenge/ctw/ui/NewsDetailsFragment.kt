package com.codingchallenge.ctw.ui

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codingchallenge.ctw.databinding.FragmentNewsDetailsBinding
import com.codingchallenge.ctw.domain.models.Article
import com.codingchallenge.ctw.utils.toDate
import com.codingchallenge.ctw.utils.toTime
import com.squareup.picasso.Picasso

class NewsDetailsFragment : Fragment() {
    private lateinit var binding: FragmentNewsDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val articleModel = it.getParcelable<Article>("article")
            binding.apply {
                articleModel?.let { article ->
                    Picasso.get().load(article.urlToImage)
                        .resize(500, 250)
                        .centerCrop()
                        .into(ivLogo)

                    tvTitle.apply {
                        text = article.title
                        paintFlags = tvTitle.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                    }

                    tvContent.text = article.content

                    tvDesc.text = article.description

                    "${article.publishedAt?.toDate()} ${article.publishedAt?.toTime()}".also {
                        tvDateTime.text = it
                    }

                    if (article.author.isNullOrEmpty()) "Source : ${article.sourceModel?.name}"
                    else "Source : ${article.sourceModel?.name} - ${article.author}".also {
                        tvSourceAuthor.text = it
                    }
                }

            }
        }
    }
}