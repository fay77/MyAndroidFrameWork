# MyAndroidFrameWork
安卓框架（初步）

利用框架层解决应用被强杀再次进入产生空指针问题以及封装解耦等。

</br>
</br>
欢迎页不需要toolBar：
</br>

``` javascript 
        setContentView(R.layout.activity_welcome ,-1 , MODE_NONE);
```

</br>
</br>
首页需要toolBar和Menu：
``` javascript 
        setContentView(R.layout.activity_main , R.string.app_name , R.menu.menu_home , MODE_HOME);

``` 
