package com.moj.nested

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.menu.MenuBuilder
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_viewpager.*

/**
 * @author : moj
 * @date : 2019/8/9
 * @description : 模拟这样一种情况，头部显示的时候，头部跟着列表滑动，当头部划出屏幕后不再出现，除非用户点击某个按钮
 */
class ViewPagerActivity : AppCompatActivity(){
    companion object{
        fun startToMe(context:Context){
            val intent = Intent(context, ViewPagerActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)

        viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager){
            override fun getItem(p0: Int): Fragment {
                return ListFragment()
            }

            override fun getCount(): Int = 4

            override fun getPageTitle(position: Int): CharSequence? {
                return "title"
            }
        }

        supportActionBar?.hide()


        //给一个按钮，用户点击可以重新弹出头部
        top.setOnClickListener {
            appBar.visibility = VISIBLE
            appBar.setExpanded(true,false)

        }

        //监听头部滑动情况，当头部完全划出屏幕看不见时隐藏它，使得滑动不会再出现头部
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { p0, p1 ->
            //实现头部消失后不再出现的功能
            if(appBar.height + p1 == 0){
                appBar.visibility = GONE
            }
        })

    }
}
