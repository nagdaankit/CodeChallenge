package com.codingchallenge.ctw.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.codingchallenge.ctw.databinding.RowNewsBinding
import com.codingchallenge.ctw.domain.models.Article
import com.codingchallenge.ctw.utils.toDate
import com.codingchallenge.ctw.utils.toTime
import com.squareup.picasso.Picasso

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    private val articles = ArrayList<Article>()

    fun setData(articles: List<Article>) {
        this.articles.clear()
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = RowNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}

class NewsViewHolder(val binding: RowNewsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var article: Article

    fun bindData(article: Article) {
        this.article = article
        binding.apply {
            Picasso.get().load(this@NewsViewHolder.article.urlToImage)
                .resize(100, 80)
                .centerCrop()
                .into(ivNewsLogo)

            tvTitle.apply {
                text = this@NewsViewHolder.article.title
                paintFlags = tvTitle.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                setOnClickListener {
                    val action =
                        NewsListFragmentDirections.actionNewsListFragmentToNewsDetailsFragment()
                    action.arguments.apply {
                        putParcelable("article", this@NewsViewHolder.article)
                    }
                    this.findNavController().navigate(action)
                }
            }
            tvDesc.text = this@NewsViewHolder.article.description
            "Published On : ${this@NewsViewHolder.article.publishedAt?.toDate()} ${this@NewsViewHolder.article.publishedAt?.toTime()}".also {
                tvPublishDate.text = it
            }
            tvAuthor.text =
                if (this@NewsViewHolder.article.author.isNullOrEmpty()) "Source : ${this@NewsViewHolder.article.sourceModel?.name}"
                else "Source : ${this@NewsViewHolder.article.sourceModel?.name} - ${this@NewsViewHolder.article.author}"
        }
    }
}