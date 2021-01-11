package com.zhongyang.newsappdemo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_news_content.*
import kotlinx.android.synthetic.main.fragment_news_content.*

class NewsContentActivity : AppCompatActivity() {

    companion object {
        /**
         * 声明一个静态方法，用于跳转时传入对应的数据
         */
        fun actionStart(context: Context, title: String, content: String) {
            //创建Intent意图，封装该类所需要的数据
            val intent = Intent(context, NewsContentActivity::class.java).apply {
                putExtra("KEY_NEWS_TITLE", title)
                putExtra("KEY_NEWS_CONTENT", content)
            }
            //启动跳转
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)
        //获取跳转传递过来的数据
        val newsTitle = intent.getStringExtra("KEY_NEWS_TITLE")//新闻标题
        val newsContent = intent.getStringExtra("KEY_NEWS_CONTENT")//新闻内容
        //将数据设置给Fragment
        if (newsTitle != null && newsContent != null) {
            //当数据不为空的时候才给
            val newsContentFragment =
                frg_newsContent as NewsContentFragment//获取NewsContentFragment的实例
            newsContentFragment.setData(newsTitle, newsContent)//调用设置数据方法，将数据给到Fragment
        }

    }
}
