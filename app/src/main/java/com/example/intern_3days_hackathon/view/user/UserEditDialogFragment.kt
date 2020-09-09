package com.example.intern_3days_hackathon.view.user

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.intern_3days_hackathon.R


class UserEditDialogFragment : DialogFragment() {

    private var KEY: String? = "key"
    private var VALUE: String? = "value"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            KEY = it.getString(ARG_PARAM1)
            VALUE = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val prefs = context?.getSharedPreferences(UserInformationViewModel.PREF, Context.MODE_PRIVATE)
            val editText = EditText(context)
            editText.hint = "新しい設定を入力してください"
            editText.setText(VALUE)
            builder.setView(editText)
                    .setTitle(returnTitle(KEY!!))
                    .setPositiveButton(R.string.update
                    ) { _, _ ->
                        prefs?.edit()?.putString(KEY, editText.text.toString())?.apply()
                    }
                    .setNegativeButton(R.string.cancel
                    ) { dialog, _ ->
                        dialog?.cancel()
                    }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun returnTitle(key: String): String {
        UserInformationViewModel.apply {
            return when (key) {
                KEY_USER_NAME -> "ユーザー名を変更します"
                KEY_FAV_WORD1 -> "お気に入りワード1を変更します"
                KEY_FAV_WORD2 -> "お気に入りワード2を変更します"
                KEY_FAV_WORD3 -> "お気に入りワード3を変更します"
                else -> "マイページURLを変更します"
            }
        }
    }

    companion object {
        private const val ARG_PARAM1 = "key"
        private const val ARG_PARAM2 = "value"

        @JvmStatic
        fun newInstance(key: String, value: String) =
                UserEditDialogFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, key)
                        putString(ARG_PARAM2, value)
                    }
                }
    }
}
