package com.example.amazingfeatures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.app.AppOpsManager
import android.os.Build
import android.content.DialogInterface
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.InvocationTargetException
import android.content.Intent
import android.view.animation.AnimationUtils
import androidx.databinding.ObservableArrayList
import com.example.amazingfeatures.universal.UniversalRecyclerViewAdapter
import com.example.amazingfeatures.universal.composite.CompositeAdapter
import com.example.amazingfeatures.universal.composite.CompositeUniversalAdapter
import com.example.amazingfeatures.universal.extensions.AquariumPlant
import com.example.amazingfeatures.universal.extensions.isGreen
import com.example.amazingfeatures.universal.extensions.isRed
import com.example.amazingfeatures.universal.extensions.pull
import com.example.amazingfeatures.universal.model.NouteModel
import com.example.amazingfeatures.universal.model.TestModel
import com.example.amazingfeatures.universal.model.TomatoModel
import com.example.amazingfeatures.universal.support.ObservableListChangedCallback


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUniversalAdapter()
//        setCompositeAdapter()
    }


    //region Composite Universal Adapter
    private val compositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(
                CompositeUniversalAdapter(
                    R.layout.item_test,
                    { item -> item is TestModel },
                    this::onTestItemClick
                )
            )
            .add(
                CompositeUniversalAdapter(
                    R.layout.item_noute,
                    { item -> item is NouteModel },
                    this::onNouteItemClick
                )
            )
            .add(
                CompositeUniversalAdapter<TomatoModel>(
                    R.layout.item_tomato,
                    { item -> item is TomatoModel }
                )
            )
            .build()
    }

    private val items by lazy {
        listOf(
            TestModel(
                "Универсальный Адаптер",
                "Изменить",
                "Удалить",
                "https://s3.ap-south-1.amazonaws.com/mindorks-server-uploads/download-show-image-glide-banner.png"
            ),
            NouteModel(
                "Это просто нечто",
                "Изменить"
            ),
            TomatoModel(
                "Отвлекаешь, помидором по лицу получаешь"
            ),
            TestModel(
                "Android или iOS?",
                "Изменить",
                "Удалить",
                ""
            ),
            TomatoModel(
                "Тшшшш, бро, работаю"
            )
        )
    }

    private fun setCompositeAdapter() {
        rvUni.adapter = compositeAdapter
        compositeAdapter.swapData(items)
    }

    private fun onTestItemClick(type: Int, model: TestModel, position: Int) {
        println("##position = $position")
        println("##model = $model")

        when (type) {
            model.actionOpen -> println("###open me please")
            model.actionEdit -> println("###edit me please")
            model.actionDelete -> println("###delete me please")
            model.actionShowImage -> println("###show image please")
        }
    }

    private fun onNouteItemClick(type: Int, model: NouteModel, position: Int) {
        println("##position = $position")
        println("##model = $model")

        when (type) {
            model.actionOpen -> println("###open me please")
            model.actionEdit -> println("###edit me please")
        }
    }
    //endregion


    //region Universal Adapter
    private val testModels = ObservableArrayList<TestModel>()
    private val testAdapter: UniversalRecyclerViewAdapter<TestModel> by lazy {
        UniversalRecyclerViewAdapter<TestModel>(
            testModels,
            R.layout.item_test,
            this::onItemClick
        )
    }

    private fun setUniversalAdapter() {
        testModels.addOnListChangedCallback(
            ObservableListChangedCallback<TestModel>(testAdapter)
        )
        rvUni.adapter = testAdapter
        testModels.add(
            TestModel(
                "Универсальный Адаптер",
                "Изменить",
                "Удалить",
                "https://s3.ap-south-1.amazonaws.com/mindorks-server-uploads/download-show-image-glide-banner.png"
            )
        )
        testModels.add(
            TestModel(
                "Android или iOS?",
                "Изменить",
                "Удалить",
                ""
            )
        )
    }

    private fun onItemClick(type: Int, model: TestModel, position: Int) {
        println("###position = $position")
        println("###model = $model")

        when (type) {
            model.actionOpen -> println("###action = open")
            model.actionEdit -> println("###action = edit")
            model.actionDelete -> println("###action = delete")
            model.actionShowImage -> println("###action = show image")
        }
    }
    //endregion


    //region experiment Features
    private val CHECK_OP_NO_THROW = "checkOpNoThrow"
    private val OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION"

    private fun collections() {
//        val list = listOf("hello", "By", "go go")
//        for (s in list.listIterator(1)) {
//            println("###s? $s")
//        }

//        val map = HashMap<String, Int>()
//        map.getOrDefault("key1", -1)

//        val map1 = hashMapOf("www1" to "value1")
//        println("###sd? ${map1.getOrElse("www2") { "default" }}")
//
//        map1.getOrPut("www1") { "s" }
//
//        println("###map1? $map1")

        val mStr = "ss "

        println("###")

        val plant = AquariumPlant("red", 2)

        println("###color1? ${plant.isGreen}")
        println("###color2? ${plant.isRed()}")
        println("###pull? ${plant.pull()}")

    }


    private fun pairsAndTriples() {
        val equipment = "fish net" to 2
        val equipment2 = Pair("fish net", 2)
        val (hey, hey2) = Pair("fish net", 2)
        val (hey3, hey4) = "fish net" to true
        println("###${equipment.component1()} used for ${equipment.second}")

        val pair = Pair("I am a String", listOf(1, 2, 3))
        println("###pair? $pair")

        val anotherPair = pair.copy(first = "I am new String")
        println("###anotherPair? $anotherPair")
    }


    fun shakeIt() {
        val shake = AnimationUtils.loadAnimation(this, R.anim.anim)
//        setting.startAnimation(shake)
    }

    fun makeText(mContext: Context, message: String, time: Int) {
        if (isNotificationEnabled(mContext)) {
            //Show Toast message
            Toast.makeText(mContext, message, time).show()
        } else {
            // Or show own custom alert dialog
            showCustomAlertDialog(mContext, message)
        }
    }

    private fun openSetting() {
        val intent = Intent()
        intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"

//for Android 5-7
        intent.putExtra("app_package", packageName)
        intent.putExtra("app_uid", applicationInfo.uid)

// for Android 8 and above
        intent.putExtra("android.provider.extra.APP_PACKAGE", packageName)

        startActivity(intent)
    }

    private fun isNotificationEnabled(mContext: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val mAppOps = mContext.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            val appInfo = mContext.getApplicationInfo()
            val pkg = mContext.getApplicationContext().getPackageName()
            val uid = appInfo.uid
            val appOpsClass: Class<*>
            try {
                appOpsClass = Class.forName(AppOpsManager::class.java.name)
                val checkOpNoThrowMethod =
                    appOpsClass.getMethod(
                        CHECK_OP_NO_THROW,
                        Integer.TYPE,
                        Integer.TYPE,
                        String::class.java
                    )

                val opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION)
                val value = opPostNotificationValue.get(Int::class.java) as Int
                return checkOpNoThrowMethod.invoke(
                    mAppOps, value, uid,
                    pkg
                ) as Int == AppOpsManager.MODE_ALLOWED
            } catch (ex: ClassNotFoundException) {
//                Utils.logExceptionCrashLytics(ex)
            } catch (ex: NoSuchMethodException) {
//                Utils.logExceptionCrashLytics(ex)
            } catch (ex: NoSuchFieldException) {
//                Utils.logExceptionCrashLytics(ex)
            } catch (ex: InvocationTargetException) {
//                Utils.logExceptionCrashLytics(ex)
            } catch (ex: IllegalAccessException) {
//                Utils.logExceptionCrashLytics(ex)
            }

            return false
        } else {
            return false
        }
    }

    private fun showCustomAlertDialog(mContext: Context, message: String) {
        if (!(mContext is Activity && (mContext as Activity).isFinishing)) {
            val mBuilder = AlertDialog.Builder(mContext)
            mBuilder.setMessage(message)
            mBuilder.setPositiveButton(mContext.getString(R.string.ok),
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            mBuilder.setCancelable(true)
            val alertDialog = mBuilder.create()
            alertDialog.show()
        }
    }

    private fun mMethod() {
        //        println("###multi ${50.multi()}")
//        println("###Kudri".greet())
//
//        val items = listOf(1, 2, 3, 4, 5)
//        val res = items.fold(1, { acc: Int, i: Int ->
//            println("###acc = $acc, i = $i")
//            val result = acc * i
//            println("###result = $result")
//            result
//        })
//        val joinedToString = items.fold("Elements", { acc, i -> "acc = $acc i = $i" })
//        println("###joined $joinedToString")
//        val product = items.fold(1, Int::times)
//        println("###product $product")

//        val stringPlus: (String, String) -> String = String::plus
//        val intPlus: Int.(Int) -> Int = Int::plus
//
//        println(stringPlus.invoke("<-", "->"))
//        println(stringPlus("Hello, ", "world!"))
//
//        println(intPlus.invoke(1, 1))
//        println(intPlus(1, 2))
//        println(2.intPlus(3)) // extension-like call
    }

    private fun funStringPlus(s1: String, s2: String): String {
        return s1.plus(s2)
    }

    private fun <T, R> Collection<T>.fold(
        initial: R,
        combine: (acc: R, nextElement: T) -> R
    ): R {
        var accumulator = initial
        for (element: T in this) {
            accumulator = combine(accumulator, element)
        }
        return accumulator
    }


    private fun Int.multi() = this * 5

    private fun String.greet() = this.plus(" we welcome you!")
    //endregion

}



