# MyAndroidFrameWork
安卓框架（初步）

利用框架层解决应用被强杀再次进入产生空指针问题以及封装解耦等。

</br>
</br>
欢迎页不需要toolBar：
</br>

        setContentView(R.layout.activity_welcome ,-1 , MODE_NONE);



</br>
</br>
比如首页需要toolBar跟Menu就可以这么写：

        setContentView(R.layout.activity_main , R.string.app_name , R.menu.menu_home , MODE_HOME);
 
</br>
    # 5.24： 新增支持listview、gridview和瀑布流的下拉刷新以及上拉显示更多自定义控件，初步效果图：
</br>

![img](https://github.com/fay77/MyAndroidFrameWork/blob/master/app/src/main/res/drawable-xxhdpi/pull_to_refresh.gif)

5.24: 新增BaseFragment，支持Fragment中的下拉刷新，加载更多列表。
