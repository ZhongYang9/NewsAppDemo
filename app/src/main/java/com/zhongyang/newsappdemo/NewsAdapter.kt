package com.zhongyang.newsappdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @项目名称 NewsAppDemo
 * @类名 NewsAdapter
 * @包名 com.zhongyang.newsappdemo
 * @创建时间 2021/1/11 16:14
 * @作者 钟阳
 * @描述 新闻标题列表的适配器
 */
class NewsAdapter(val titleList: ArrayList<News>) :
    RecyclerView.Adapter<NewsAdapter.InnerHolder>() {

    private var mOnNewsItemClickListener: OnNewsItemClickListener? = null

    inner class InnerHolder(view: View) : RecyclerView.ViewHolder(view) {
        //找控件
        val mNewsTitle: TextView = view.findViewById(R.id.tv_newslIst_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.InnerHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_news_item, parent, false)
        return InnerHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsAdapter.InnerHolder, position: Int) {
        //获取当前条目的实例
        val news = titleList[position]
        //给控件设置显示内容
        holder.mNewsTitle.text = news.title
        //设置条目的点击事件
        holder.itemView.setOnClickListener {
            mOnNewsItemClickListener?.newsItemClick(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    /**
     * 暴露监听方法
     */
    public fun setOnNewsItemClickListener(listener: OnNewsItemClickListener) {
        this.mOnNewsItemClickListener = listener
    }

    /**
     * 定义监听接口
     */
    interface OnNewsItemClickListener {
        //点击事件
        fun newsItemClick(holder: InnerHolder, position: Int)
    }
}