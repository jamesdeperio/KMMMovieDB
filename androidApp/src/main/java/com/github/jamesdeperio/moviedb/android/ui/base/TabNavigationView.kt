package com.github.jamesdeperio.moviedb.android.ui.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import com.github.jamesdeperio.moviedb.android.R
import com.github.jamesdeperio.moviedb.android.databinding.ViewTabNavBinding

class TabNavigationView:LinearLayout {

    private lateinit var binding: ViewTabNavBinding
    private val tabViews: MutableList<Button> = ArrayList()
    private var selectedColor = -1
    private var selectedBackground: Drawable?= null
    private var color = -1
    private var containerBackground: Drawable? = null
    private var currentSelected:String = ""

    constructor(context: Context?) : super(context) {
      initialize(context,null,0,0)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initialize(context,attrs,0,0)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initialize(context,attrs,defStyleAttr,0)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initialize(context, attrs, defStyleAttr, defStyleRes)
    }


    private fun initialize(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) {
        if (context==null) return

        attrs.let {
            val a = context.obtainStyledAttributes(attrs, R.styleable.TabNavigationView, defStyleAttr, defStyleRes)
            selectedColor = a.getColor(R.styleable.TabNavigationView_selectedTextColor,
                Color.WHITE )
            color = a.getColor(R.styleable.TabNavigationView_textColor,
                context.resources.getColor(R.color.colorPrimaryDark,null) )

            selectedBackground = a.getDrawable(R.styleable.TabNavigationView_selectedBackground)
            containerBackground = a.getDrawable(R.styleable.TabNavigationView_containerBackground)

            a.recycle()
        }

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_tab_nav, this, true)
        binding = ViewTabNavBinding.inflate(inflater, this, true)
        binding.tabContainer.background = containerBackground
    }


    fun addTab( title:String, callback:()->Unit) {
        val button = Button(context,null,R.style.Widget_AppCompat_Button_Borderless)
        button.text = title
        button.isAllCaps =false
        button.isFocusable = true
        button.isClickable = true
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        button.setBackgroundResource(outValue.resourceId)
        button.setTextSize(TypedValue.COMPLEX_UNIT_DIP,13f)
        button.setTextColor(color)
        button.setPadding(35,15,35,15)
        button.setOnThrottleClickListener {
            if (currentSelected == button.text.toString()) return@setOnThrottleClickListener
            tabViews.forEach { tab->
                tab.background = null
                tab.setTextColor(color)
                tab.requestLayout()
            }
            currentSelected = button.text.toString()
            button.setBackgroundColor(Color.TRANSPARENT)
            button.background = selectedBackground
            button.setTextColor(selectedColor)
            callback()
        }
        tabViews.add(button)
        if (tabViews.size ==1) {
            button.setBackgroundColor(Color.TRANSPARENT)
            button.background = selectedBackground
            button.setTextColor(selectedColor)
            currentSelected = button.text.toString()
        }
        binding.tabContainer.addView(button)
    }

}
