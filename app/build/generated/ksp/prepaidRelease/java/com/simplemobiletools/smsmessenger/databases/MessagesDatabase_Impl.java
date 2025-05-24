package com.simplemobiletools.smsmessenger.databases;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.simplemobiletools.smsmessenger.interfaces.AttachmentsDao;
import com.simplemobiletools.smsmessenger.interfaces.AttachmentsDao_Impl;
import com.simplemobiletools.smsmessenger.interfaces.ConversationsDao;
import com.simplemobiletools.smsmessenger.interfaces.ConversationsDao_Impl;
import com.simplemobiletools.smsmessenger.interfaces.MessageAttachmentsDao;
import com.simplemobiletools.smsmessenger.interfaces.MessageAttachmentsDao_Impl;
import com.simplemobiletools.smsmessenger.interfaces.MessagesDao;
import com.simplemobiletools.smsmessenger.interfaces.MessagesDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MessagesDatabase_Impl extends MessagesDatabase {
  private volatile ConversationsDao _conversationsDao;

  private volatile AttachmentsDao _attachmentsDao;

  private volatile MessageAttachmentsDao _messageAttachmentsDao;

  private volatile MessagesDao _messagesDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(8) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `conversations` (`thread_id` INTEGER NOT NULL, `snippet` TEXT NOT NULL, `date` INTEGER NOT NULL, `read` INTEGER NOT NULL, `title` TEXT NOT NULL, `photo_uri` TEXT NOT NULL, `is_group_conversation` INTEGER NOT NULL, `phone_number` TEXT NOT NULL, `is_scheduled` INTEGER NOT NULL, `uses_custom_title` INTEGER NOT NULL, `archived` INTEGER NOT NULL, PRIMARY KEY(`thread_id`))");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_conversations_thread_id` ON `conversations` (`thread_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `attachments` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `message_id` INTEGER NOT NULL, `uri_string` TEXT NOT NULL, `mimetype` TEXT NOT NULL, `width` INTEGER NOT NULL, `height` INTEGER NOT NULL, `filename` TEXT NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_attachments_message_id` ON `attachments` (`message_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `message_attachments` (`id` INTEGER NOT NULL, `text` TEXT NOT NULL, `attachments` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `messages` (`id` INTEGER NOT NULL, `body` TEXT NOT NULL, `type` INTEGER NOT NULL, `status` INTEGER NOT NULL, `participants` TEXT NOT NULL, `date` INTEGER NOT NULL, `read` INTEGER NOT NULL, `thread_id` INTEGER NOT NULL, `is_mms` INTEGER NOT NULL, `attachment` TEXT, `sender_phone_number` TEXT NOT NULL, `sender_name` TEXT NOT NULL, `sender_photo_uri` TEXT NOT NULL, `subscription_id` INTEGER NOT NULL, `is_scheduled` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `recycle_bin_messages` (`id` INTEGER NOT NULL, `deleted_ts` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_recycle_bin_messages_id` ON `recycle_bin_messages` (`id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '23811e41b338a810cf5df26a5dff67a5')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `conversations`");
        db.execSQL("DROP TABLE IF EXISTS `attachments`");
        db.execSQL("DROP TABLE IF EXISTS `message_attachments`");
        db.execSQL("DROP TABLE IF EXISTS `messages`");
        db.execSQL("DROP TABLE IF EXISTS `recycle_bin_messages`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsConversations = new HashMap<String, TableInfo.Column>(11);
        _columnsConversations.put("thread_id", new TableInfo.Column("thread_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversations.put("snippet", new TableInfo.Column("snippet", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversations.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversations.put("read", new TableInfo.Column("read", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversations.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversations.put("photo_uri", new TableInfo.Column("photo_uri", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversations.put("is_group_conversation", new TableInfo.Column("is_group_conversation", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversations.put("phone_number", new TableInfo.Column("phone_number", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversations.put("is_scheduled", new TableInfo.Column("is_scheduled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversations.put("uses_custom_title", new TableInfo.Column("uses_custom_title", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversations.put("archived", new TableInfo.Column("archived", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysConversations = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesConversations = new HashSet<TableInfo.Index>(1);
        _indicesConversations.add(new TableInfo.Index("index_conversations_thread_id", true, Arrays.asList("thread_id"), Arrays.asList("ASC")));
        final TableInfo _infoConversations = new TableInfo("conversations", _columnsConversations, _foreignKeysConversations, _indicesConversations);
        final TableInfo _existingConversations = TableInfo.read(db, "conversations");
        if (!_infoConversations.equals(_existingConversations)) {
          return new RoomOpenHelper.ValidationResult(false, "conversations(com.simplemobiletools.smsmessenger.models.Conversation).\n"
                  + " Expected:\n" + _infoConversations + "\n"
                  + " Found:\n" + _existingConversations);
        }
        final HashMap<String, TableInfo.Column> _columnsAttachments = new HashMap<String, TableInfo.Column>(7);
        _columnsAttachments.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("message_id", new TableInfo.Column("message_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("uri_string", new TableInfo.Column("uri_string", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("mimetype", new TableInfo.Column("mimetype", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("width", new TableInfo.Column("width", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("height", new TableInfo.Column("height", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("filename", new TableInfo.Column("filename", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAttachments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAttachments = new HashSet<TableInfo.Index>(1);
        _indicesAttachments.add(new TableInfo.Index("index_attachments_message_id", true, Arrays.asList("message_id"), Arrays.asList("ASC")));
        final TableInfo _infoAttachments = new TableInfo("attachments", _columnsAttachments, _foreignKeysAttachments, _indicesAttachments);
        final TableInfo _existingAttachments = TableInfo.read(db, "attachments");
        if (!_infoAttachments.equals(_existingAttachments)) {
          return new RoomOpenHelper.ValidationResult(false, "attachments(com.simplemobiletools.smsmessenger.models.Attachment).\n"
                  + " Expected:\n" + _infoAttachments + "\n"
                  + " Found:\n" + _existingAttachments);
        }
        final HashMap<String, TableInfo.Column> _columnsMessageAttachments = new HashMap<String, TableInfo.Column>(3);
        _columnsMessageAttachments.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessageAttachments.put("text", new TableInfo.Column("text", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessageAttachments.put("attachments", new TableInfo.Column("attachments", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMessageAttachments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMessageAttachments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMessageAttachments = new TableInfo("message_attachments", _columnsMessageAttachments, _foreignKeysMessageAttachments, _indicesMessageAttachments);
        final TableInfo _existingMessageAttachments = TableInfo.read(db, "message_attachments");
        if (!_infoMessageAttachments.equals(_existingMessageAttachments)) {
          return new RoomOpenHelper.ValidationResult(false, "message_attachments(com.simplemobiletools.smsmessenger.models.MessageAttachment).\n"
                  + " Expected:\n" + _infoMessageAttachments + "\n"
                  + " Found:\n" + _existingMessageAttachments);
        }
        final HashMap<String, TableInfo.Column> _columnsMessages = new HashMap<String, TableInfo.Column>(15);
        _columnsMessages.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("body", new TableInfo.Column("body", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("type", new TableInfo.Column("type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("participants", new TableInfo.Column("participants", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("read", new TableInfo.Column("read", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("thread_id", new TableInfo.Column("thread_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("is_mms", new TableInfo.Column("is_mms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("attachment", new TableInfo.Column("attachment", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("sender_phone_number", new TableInfo.Column("sender_phone_number", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("sender_name", new TableInfo.Column("sender_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("sender_photo_uri", new TableInfo.Column("sender_photo_uri", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("subscription_id", new TableInfo.Column("subscription_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("is_scheduled", new TableInfo.Column("is_scheduled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMessages = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMessages = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMessages = new TableInfo("messages", _columnsMessages, _foreignKeysMessages, _indicesMessages);
        final TableInfo _existingMessages = TableInfo.read(db, "messages");
        if (!_infoMessages.equals(_existingMessages)) {
          return new RoomOpenHelper.ValidationResult(false, "messages(com.simplemobiletools.smsmessenger.models.Message).\n"
                  + " Expected:\n" + _infoMessages + "\n"
                  + " Found:\n" + _existingMessages);
        }
        final HashMap<String, TableInfo.Column> _columnsRecycleBinMessages = new HashMap<String, TableInfo.Column>(2);
        _columnsRecycleBinMessages.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecycleBinMessages.put("deleted_ts", new TableInfo.Column("deleted_ts", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRecycleBinMessages = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRecycleBinMessages = new HashSet<TableInfo.Index>(1);
        _indicesRecycleBinMessages.add(new TableInfo.Index("index_recycle_bin_messages_id", true, Arrays.asList("id"), Arrays.asList("ASC")));
        final TableInfo _infoRecycleBinMessages = new TableInfo("recycle_bin_messages", _columnsRecycleBinMessages, _foreignKeysRecycleBinMessages, _indicesRecycleBinMessages);
        final TableInfo _existingRecycleBinMessages = TableInfo.read(db, "recycle_bin_messages");
        if (!_infoRecycleBinMessages.equals(_existingRecycleBinMessages)) {
          return new RoomOpenHelper.ValidationResult(false, "recycle_bin_messages(com.simplemobiletools.smsmessenger.models.RecycleBinMessage).\n"
                  + " Expected:\n" + _infoRecycleBinMessages + "\n"
                  + " Found:\n" + _existingRecycleBinMessages);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "23811e41b338a810cf5df26a5dff67a5", "11b019a4353dfe6c209f34dab7a68382");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "conversations","attachments","message_attachments","messages","recycle_bin_messages");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `conversations`");
      _db.execSQL("DELETE FROM `attachments`");
      _db.execSQL("DELETE FROM `message_attachments`");
      _db.execSQL("DELETE FROM `messages`");
      _db.execSQL("DELETE FROM `recycle_bin_messages`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ConversationsDao.class, ConversationsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AttachmentsDao.class, AttachmentsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MessageAttachmentsDao.class, MessageAttachmentsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MessagesDao.class, MessagesDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ConversationsDao ConversationsDao() {
    if (_conversationsDao != null) {
      return _conversationsDao;
    } else {
      synchronized(this) {
        if(_conversationsDao == null) {
          _conversationsDao = new ConversationsDao_Impl(this);
        }
        return _conversationsDao;
      }
    }
  }

  @Override
  public AttachmentsDao AttachmentsDao() {
    if (_attachmentsDao != null) {
      return _attachmentsDao;
    } else {
      synchronized(this) {
        if(_attachmentsDao == null) {
          _attachmentsDao = new AttachmentsDao_Impl(this);
        }
        return _attachmentsDao;
      }
    }
  }

  @Override
  public MessageAttachmentsDao MessageAttachmentsDao() {
    if (_messageAttachmentsDao != null) {
      return _messageAttachmentsDao;
    } else {
      synchronized(this) {
        if(_messageAttachmentsDao == null) {
          _messageAttachmentsDao = new MessageAttachmentsDao_Impl(this);
        }
        return _messageAttachmentsDao;
      }
    }
  }

  @Override
  public MessagesDao MessagesDao() {
    if (_messagesDao != null) {
      return _messagesDao;
    } else {
      synchronized(this) {
        if(_messagesDao == null) {
          _messagesDao = new MessagesDao_Impl(this);
        }
        return _messagesDao;
      }
    }
  }
}
