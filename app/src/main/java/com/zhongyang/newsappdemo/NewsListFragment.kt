package com.zhongyang.newsappdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_news_list.*
import java.util.ArrayList

/**
 * @项目名称 NewsAppDemo
 * @类名 NewsListFragment
 * @包名 com.zhongyang.newsappdemo
 * @创建时间 2021/1/11 15:57
 * @作者 钟阳
 * @描述
 */
class NewsListFragment : Fragment(), NewsAdapter.OnNewsItemClickListener {

    var mIsTwoPane = false//定义一个标识量，是否为双页
    private val mNewsList = ArrayList<News>()//创建集合

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //如果有fl_newsContentLayout的控件就是双页模式，否则就是单页模式
        mIsTwoPane = activity?.findViewById<View>(R.id.fl_newsContentLayout) != null
        //初始化适配器
        initAdapter()
    }

    private fun initAdapter() {
        rv_newsTitle_list.layoutManager = LinearLayoutManager(context)//设置布局管理器
        val newsAdapter = NewsAdapter(getNewsData())//实例化适配器
        rv_newsTitle_list.adapter = newsAdapter
        newsAdapter.setOnNewsItemClickListener(this)//设置适配器的点击事件
    }

    private fun getNewsData(): ArrayList<News> {
        //创建数据
        for (i in 1..50) {
            val news = News("新闻表标题-$i", createNewsContent("新闻内容$i"))
            //添加到集合
            mNewsList.add(news)
        }
        return mNewsList
    }

    /**
     * 创建新闻内容方法
     */
    private fun createNewsContent(content: String): String {
        val n = (1..20).random()//创建一个1到20区间内的随机数
        val builder = StringBuilder()//实例化StringBuilder类
        //循环n次
        repeat(n) {
            //追加内容就是新闻内容
            builder.append(content)
        }
        return builder.toString()
    }

    override fun newsItemClick(holder: NewsAdapter.InnerHolder, position: Int) {
        //获取当前条目实例
        val news = mNewsList[position]
        //判断是否为双页模式
        if (mIsTwoPane) {
            //如果是双页模式，就将数据设置给Fragment
            showNewsContent(news)
        } else {
            //如果是单页模式，就启动NewsContentActivity
            context?.let { NewsContentActivity.actionStart(it, news.title, news.content) }
        }
    }

    private fun showNewsContent(news: News) {
        val newsContentFragment = frg_newsContent as NewsContentFragment
        newsContentFragment.setData(news.title, news.content)
    }
}