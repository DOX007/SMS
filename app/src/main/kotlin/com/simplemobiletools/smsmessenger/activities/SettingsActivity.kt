package com.simplemobiletools.smsmessenger.activities

import com.simplemobiletools.smsmessenger.helpers.showDoxInfoDialog
import com.simplemobiletools.smsmessenger.helpers.SharedPrefsHelper
import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import com.simplemobiletools.commons.activities.ManageBlockedNumbersActivity
import com.simplemobiletools.commons.dialogs.*
import com.simplemobiletools.commons.extensions.*
import com.simplemobiletools.commons.helpers.*
import com.simplemobiletools.commons.models.RadioItem
import com.simplemobiletools.smsmessenger.R
import com.simplemobiletools.smsmessenger.databinding.ActivitySettingsBinding
import com.simplemobiletools.smsmessenger.dialogs.ExportMessagesDialog
import com.simplemobiletools.smsmessenger.extensions.config
import com.simplemobiletools.smsmessenger.extensions.emptyMessagesRecycleBin
import com.simplemobiletools.smsmessenger.extensions.messagesDB
import com.simplemobiletools.smsmessenger.helpers.*
import com.simplemobiletools.smsmessenger.models.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*
import kotlin.system.exitProcess
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT

class SettingsActivity : SimpleActivity() {
    private var blockedNumbersAtPause = -1
    private var recycleBinMessages = 0
    private val messagesFileType = "application/json"
    private val messageImportFileTypes = listOf("application/json", "application/xml", "text/xml")

    private val binding by viewBinding(ActivitySettingsBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
    isMaterialActivity = true
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    updateMaterialActivityViews(
        mainCoordinatorLayout = binding.settingsCoordinator,
        nestedView = binding.settingsHolder,
        useTransparentNavigation = true,
        useTopSearchMenu = false
    )
    setupMaterialScrollListener(scrollingView = binding.settingsNestedScrollview, toolbar = binding.settingsToolbar)

    // Dold meny: visa vitlistan efter 7 klick på DOX info-holder
    var doxClickCount = 0
    findViewById<View>(R.id.settings_dox_info_holder)?.setOnClickListener {
        doxClickCount++
        if (doxClickCount == 7) {
            showWhitelistEditor()
            doxClickCount = 0
        }


    // Dold meny: visa ...Intent-raden efter 7 klick på "...More / ...Less / ...Intent"
    val doxInfoLabel = findViewById<View>(R.id.settings_dox_info_label)
    val intentLabel = findViewById<View>(R.id.settings_dox_intent_label)
    intentLabel?.visibility = View.GONE

    var doxInfoTapCount = 0
    val DOX_INTENT_THRESHOLD = 7

    doxInfoLabel?.setOnClickListener {
        doxInfoTapCount++
        if (doxInfoTapCount >= DOX_INTENT_THRESHOLD) {
            intentLabel?.visibility = View.VISIBLE
        }
    }

    intentLabel?.setOnClickListener {
        AlertDialog.Builder(this)
            .setTitle("...Intent")
            .setMessage("Om du skickar ett SMS eller MMS med texten ...Intent till denna enhet raderas hela chatten för det numret tyst ur appen.")
            .setPositiveButton("OK", null)
            .show()
    }
}

}

    private fun showWhitelistEditor() {
    val prefs = SharedPrefsHelper(this)
    val whitelist = prefs.getWhitelistedNumbers().toMutableList()

    val dialogView = layoutInflater.inflate(R.layout.dialog_whitelist_editor, null)
    val container = dialogView.findViewById<LinearLayout>(R.id.whitelist_container)
    val input = dialogView.findViewById<EditText>(R.id.whitelist_input)
    val addBtn = dialogView.findViewById<Button>(R.id.whitelist_add_btn)

    // NYTT: Hantera header/rubrik till Telegram-logg
    val headerInput = dialogView.findViewById<EditText>(R.id.header_input)
    headerInput.setText(prefs.getCustomLogHeader())

    fun refreshList() {
        container.removeAllViews()
        whitelist.forEach { number ->
            val row = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                val label = TextView(this@SettingsActivity).apply {
                    text = number
                    layoutParams = LinearLayout.LayoutParams(0, WRAP_CONTENT, 1f)
                }
                val remove = Button(this@SettingsActivity).apply {
                    text = "X"
                    setOnClickListener {
                        whitelist.remove(number)
                        refreshList()
                    }
                }
                addView(label)
                addView(remove)
            }
            container.addView(row)
        }
    }

    refreshList()

    val dialog = AlertDialog.Builder(this)
        .setTitle("Vitlistade nummer")
        .setView(dialogView)
        .setPositiveButton("Spara") { _, _ ->
            prefs.setWhitelistedNumbers(whitelist)
            // NYTT: Spara även namn/rubrik till Telegram
            prefs.setCustomLogHeader(headerInput.text.toString())
        }
        .setNegativeButton("Avbryt", null)
        .create()

    addBtn.setOnClickListener {
        val num = input.text.toString().trim()
        if (num.isNotEmpty() && !whitelist.contains(num)) {
            whitelist.add(num)
            input.text.clear()
            refreshList()
        }
    }

    dialog.show()

}
    override fun onResume() {
        super.onResume()
        setupToolbar(binding.settingsToolbar, NavigationIcon.Arrow)


        setupCustomizeColors()
        setupCustomizeNotifications()
        setupUseEnglish()
        setupLanguage()
        setupManageBlockedNumbers()
        setupManageBlockedKeywords()
        setupChangeDateTimeFormat()
        setupFontSize()
        setupShowCharacterCounter()
        setupUseSimpleCharacters()
        setupEnableDeliveryReports()
        setupSendLongMessageAsMMS()
        setupGroupMessageAsMMS()
        setupLockScreenVisibility()
        setupMMSFileSizeLimit()
        setupUseRecycleBin()
        setupEmptyRecycleBin()
        setupAppPasswordProtection()
        setupMessagesImport()
        setupDisableSendButtonToggle()
        updateTextColors(binding.settingsNestedScrollview)


        if (blockedNumbersAtPause != -1 && blockedNumbersAtPause != getBlockedNumbers().hashCode()) {
            refreshMessages()
        }

        arrayOf(
            binding.settingsColorCustomizationSectionLabel,
            binding.settingsGeneralSettingsLabel,
            binding.settingsOutgoingMessagesLabel,
            binding.settingsNotificationsLabel,
            binding.settingsRecycleBinLabel,
            binding.settingsSecurityLabel,
            binding.settingsMigratingLabel
        ).forEach {
            it.setTextColor(getProperPrimaryColor())
        }
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        if (uri != null) {
            MessagesImporter(this).importMessages(uri)
        }
    }

    private val saveDocument = registerForActivityResult(ActivityResultContracts.CreateDocument(messagesFileType)) { uri ->
        if (uri != null) {
            toast(com.simplemobiletools.commons.R.string.exporting)
            exportMessages(uri)
        }
    }



    private fun setupMessagesImport() {
        binding.settingsImportMessagesHolder.setOnClickListener {
            getContent.launch(messageImportFileTypes.toTypedArray())
        }
    }

    private fun exportMessages(uri: Uri) {
        ensureBackgroundThread {
            try {
                MessagesReader(this).getMessagesToExport(config.exportSms, config.exportMms) { messagesToExport ->
                    if (messagesToExport.isEmpty()) {
                        toast(com.simplemobiletools.commons.R.string.no_entries_for_exporting)
                        return@getMessagesToExport
                    }
                    val json = Json { encodeDefaults = true }
                    val jsonString = json.encodeToString(messagesToExport)
                    val outputStream = contentResolver.openOutputStream(uri)!!

                    outputStream.use {
                        it.write(jsonString.toByteArray())
                    }
                    toast(com.simplemobiletools.commons.R.string.exporting_successful)
                }
            } catch (e: Exception) {
                showErrorToast(e)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        blockedNumbersAtPause = getBlockedNumbers().hashCode()
    }

    private fun setupCustomizeColors() = binding.apply {
        settingsColorCustomizationLabel.text = getCustomizeColorsString()
        settingsColorCustomizationHolder.setOnClickListener {
            handleCustomizeColorsClick()
        }
    }

    private fun setupCustomizeNotifications() = binding.apply {
        settingsCustomizeNotificationsHolder.beVisibleIf(isOreoPlus())
        settingsCustomizeNotificationsHolder.setOnClickListener {
            launchCustomizeNotificationsIntent()
        }
    }

    private fun setupUseEnglish() = binding.apply {
        settingsUseEnglishHolder.beVisibleIf((config.wasUseEnglishToggled || Locale.getDefault().language != "en") && !isTiramisuPlus())
        settingsUseEnglish.isChecked = config.useEnglish
        settingsUseEnglishHolder.setOnClickListener {
            settingsUseEnglish.toggle()
            config.useEnglish = settingsUseEnglish.isChecked
            exitProcess(0)
        }
    }

    private fun setupLanguage() = binding.apply {
        settingsLanguage.text = Locale.getDefault().displayLanguage
        settingsLanguageHolder.beVisibleIf(isTiramisuPlus())
        settingsLanguageHolder.setOnClickListener {
            launchChangeAppLanguageIntent()
        }
    }

    // support for device-wise blocking came on Android 7, rely only on that
    @TargetApi(Build.VERSION_CODES.N)
    private fun setupManageBlockedNumbers() = binding.apply {
        settingsManageBlockedNumbers.text = getString(com.simplemobiletools.commons.R.string.manage_blocked_numbers)
        settingsManageBlockedNumbersHolder.beVisibleIf(isNougatPlus())

        settingsManageBlockedNumbersHolder.setOnClickListener {
            Intent(this@SettingsActivity, ManageBlockedNumbersActivity::class.java).apply {
                startActivity(this)
        }
    }
}

    private fun setupManageBlockedKeywords() = binding.apply {
        settingsManageBlockedKeywords.text = getString(R.string.manage_blocked_keywords)

        settingsManageBlockedKeywordsHolder.setOnClickListener {
            Intent(this@SettingsActivity, ManageBlockedKeywordsActivity::class.java).apply {
                startActivity(this)
        }
    }
}

    private fun setupChangeDateTimeFormat() = binding.apply {
        settingsChangeDateTimeFormatHolder.setOnClickListener {
            ChangeDateTimeFormatDialog(this@SettingsActivity) {
                refreshMessages()
            }
        }
    }

    private fun setupFontSize() = binding.apply {
        settingsFontSize.text = getFontSizeText()
        settingsFontSizeHolder.setOnClickListener {
            val items = arrayListOf(
                RadioItem(FONT_SIZE_SMALL, getString(com.simplemobiletools.commons.R.string.small)),
                RadioItem(FONT_SIZE_MEDIUM, getString(com.simplemobiletools.commons.R.string.medium)),
                RadioItem(FONT_SIZE_LARGE, getString(com.simplemobiletools.commons.R.string.large)),
                RadioItem(FONT_SIZE_EXTRA_LARGE, getString(com.simplemobiletools.commons.R.string.extra_large))
            )

            RadioGroupDialog(this@SettingsActivity, items, config.fontSize) {
                config.fontSize = it as Int
                settingsFontSize.text = getFontSizeText()
            }
        }
    }
    private fun setupDisableSendButtonToggle() = binding.apply {
        settingsDisableSendButtonHolder.beVisible()
        settingsDisableSendButton.isChecked = config.disableSendButton
        settingsDisableSendButtonHolder.setOnClickListener {
            settingsDisableSendButton.toggle()
            config.disableSendButton = settingsDisableSendButton.isChecked
        }
    }

    private fun setupShowCharacterCounter() = binding.apply {
        settingsShowCharacterCounter.isChecked = config.showCharacterCounter
        settingsShowCharacterCounterHolder.setOnClickListener {
            settingsShowCharacterCounter.toggle()
            config.showCharacterCounter = settingsShowCharacterCounter.isChecked
        }
    }

    private fun setupUseSimpleCharacters() = binding.apply {
        settingsUseSimpleCharacters.isChecked = config.useSimpleCharacters
        settingsUseSimpleCharactersHolder.setOnClickListener {
            settingsUseSimpleCharacters.toggle()
            config.useSimpleCharacters = settingsUseSimpleCharacters.isChecked
        }
    }


    private fun setupEnableDeliveryReports() = binding.apply {
        settingsEnableDeliveryReports.isChecked = config.enableDeliveryReports
        settingsEnableDeliveryReportsHolder.setOnClickListener {
            settingsEnableDeliveryReports.toggle()
            config.enableDeliveryReports = settingsEnableDeliveryReports.isChecked
        }
    }

    private fun setupSendLongMessageAsMMS() = binding.apply {
        settingsSendLongMessageMms.isChecked = config.sendLongMessageMMS
        settingsSendLongMessageMmsHolder.setOnClickListener {
            settingsSendLongMessageMms.toggle()
            config.sendLongMessageMMS = settingsSendLongMessageMms.isChecked
        }
    }

    private fun setupGroupMessageAsMMS() = binding.apply {
        settingsSendGroupMessageMms.isChecked = config.sendGroupMessageMMS
        settingsSendGroupMessageMmsHolder.setOnClickListener {
            settingsSendGroupMessageMms.toggle()
            config.sendGroupMessageMMS = settingsSendGroupMessageMms.isChecked
        }
    }

    private fun setupLockScreenVisibility() = binding.apply {
        settingsLockScreenVisibility.text = getLockScreenVisibilityText()
        settingsLockScreenVisibilityHolder.setOnClickListener {
            val items = arrayListOf(
                RadioItem(LOCK_SCREEN_SENDER_MESSAGE, getString(R.string.sender_and_message)),
                RadioItem(LOCK_SCREEN_SENDER, getString(R.string.sender_only)),
                RadioItem(LOCK_SCREEN_NOTHING, getString(com.simplemobiletools.commons.R.string.nothing)),
            )

            RadioGroupDialog(this@SettingsActivity, items, config.lockScreenVisibilitySetting) {
                config.lockScreenVisibilitySetting = it as Int
                settingsLockScreenVisibility.text = getLockScreenVisibilityText()
            }
        }
    }

    private fun getLockScreenVisibilityText() = getString(
        when (config.lockScreenVisibilitySetting) {
            LOCK_SCREEN_SENDER_MESSAGE -> R.string.sender_and_message
            LOCK_SCREEN_SENDER -> R.string.sender_only
            else -> com.simplemobiletools.commons.R.string.nothing
        }
    )

    private fun setupMMSFileSizeLimit() = binding.apply {
        settingsMmsFileSizeLimit.text = getMMSFileLimitText()
        settingsMmsFileSizeLimitHolder.setOnClickListener {
            val items = arrayListOf(
                RadioItem(7, getString(R.string.mms_file_size_limit_none), FILE_SIZE_NONE),
                RadioItem(6, getString(R.string.mms_file_size_limit_2mb), FILE_SIZE_2_MB),
                RadioItem(5, getString(R.string.mms_file_size_limit_1mb), FILE_SIZE_1_MB),
                RadioItem(4, getString(R.string.mms_file_size_limit_600kb), FILE_SIZE_600_KB),
                RadioItem(3, getString(R.string.mms_file_size_limit_300kb), FILE_SIZE_300_KB),
                RadioItem(2, getString(R.string.mms_file_size_limit_200kb), FILE_SIZE_200_KB),
                RadioItem(1, getString(R.string.mms_file_size_limit_100kb), FILE_SIZE_100_KB),
            )

            val checkedItemId = items.find { it.value == config.mmsFileSizeLimit }?.id ?: 7
            RadioGroupDialog(this@SettingsActivity, items, checkedItemId) {
                config.mmsFileSizeLimit = it as Long
                settingsMmsFileSizeLimit.text = getMMSFileLimitText()
            }
        }
    }

    private fun setupUseRecycleBin() = binding.apply {
        updateRecycleBinButtons()
        settingsUseRecycleBin.isChecked = config.useRecycleBin
        settingsUseRecycleBinHolder.setOnClickListener {
            settingsUseRecycleBin.toggle()
            config.useRecycleBin = settingsUseRecycleBin.isChecked
            updateRecycleBinButtons()
        }
    }

    private fun updateRecycleBinButtons() = binding.apply {
        settingsEmptyRecycleBinHolder.beVisibleIf(config.useRecycleBin)
    }

    private fun setupEmptyRecycleBin() = binding.apply {
        ensureBackgroundThread {
            recycleBinMessages = messagesDB.getArchivedCount()
            runOnUiThread {
                settingsEmptyRecycleBinSize.text =
                    resources.getQuantityString(R.plurals.delete_messages, recycleBinMessages, recycleBinMessages)
            }
        }

        settingsEmptyRecycleBinHolder.setOnClickListener {
            if (recycleBinMessages == 0) {
                toast(com.simplemobiletools.commons.R.string.recycle_bin_empty)
            } else {
                ConfirmationDialog(
                    activity = this@SettingsActivity,
                    message = "",
                    messageId = R.string.empty_recycle_bin_messages_confirmation,
                    positive = com.simplemobiletools.commons.R.string.yes,
                    negative = com.simplemobiletools.commons.R.string.no
                ) {
                    ensureBackgroundThread {
                        emptyMessagesRecycleBin()
                    }
                    recycleBinMessages = 0
                    settingsEmptyRecycleBinSize.text =
                        resources.getQuantityString(R.plurals.delete_messages, recycleBinMessages, recycleBinMessages)
                }
            }
        }
    }

    private fun setupAppPasswordProtection() = binding.apply {
        settingsAppPasswordProtection.isChecked = config.isAppPasswordProtectionOn
        settingsAppPasswordProtectionHolder.setOnClickListener {
            val tabToShow = if (config.isAppPasswordProtectionOn) config.appProtectionType else SHOW_ALL_TABS
            SecurityDialog(this@SettingsActivity, config.appPasswordHash, tabToShow) { hash, type, success ->
                if (success) {
                    val hasPasswordProtection = config.isAppPasswordProtectionOn
                    settingsAppPasswordProtection.isChecked = !hasPasswordProtection
                    config.isAppPasswordProtectionOn = !hasPasswordProtection
                    config.appPasswordHash = if (hasPasswordProtection) "" else hash
                    config.appProtectionType = type

                    if (config.isAppPasswordProtectionOn) {
                        val confirmationTextId = if (config.appProtectionType == PROTECTION_FINGERPRINT) {
                            com.simplemobiletools.commons.R.string.fingerprint_setup_successfully
                        } else {
                            com.simplemobiletools.commons.R.string.protection_setup_successfully
                        }

                        ConfirmationDialog(this@SettingsActivity, "", confirmationTextId, com.simplemobiletools.commons.R.string.ok, 0) { }
                    }
                }
            }
        }
    }

    private fun getMMSFileLimitText() = getString(
        when (config.mmsFileSizeLimit) {
            FILE_SIZE_100_KB -> R.string.mms_file_size_limit_100kb
            FILE_SIZE_200_KB -> R.string.mms_file_size_limit_200kb
            FILE_SIZE_300_KB -> R.string.mms_file_size_limit_300kb
            FILE_SIZE_600_KB -> R.string.mms_file_size_limit_600kb
            FILE_SIZE_1_MB -> R.string.mms_file_size_limit_1mb
            FILE_SIZE_2_MB -> R.string.mms_file_size_limit_2mb
            else -> R.string.mms_file_size_limit_none
        }
    )
}
