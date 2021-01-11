package com.zhongyang.newsappdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_news_content.*

/**
 * @项目名称 NewsAppDemo
 * @类名 NewsContentFragment
 * @包名 com.zhongyang.newsappdemo
 * @创建时间 2021/1/11 15:28
 * @作者 钟阳
 * @描述 新闻内容碎片
 */
class NewsContentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context)
            .inflate(R.layout.fragment_news_content, container, false)
    }

    /**
     * 提供设置数据的方法
     */
    fun setData(title: String, content: String) {
        //首先要让内容布局可见，提示布局隐藏
        cl_newCon.visibility = VISIBLE
        cl_newCon_normal.visibility = GONE
        //设置控件的显示内容
        tv_newsTitle.text = title
        tv_newsContent.text = content
    }
}