package com.simplemobiletools.smsmessenger.interfaces;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.simplemobiletools.commons.models.SimpleContact;
import com.simplemobiletools.smsmessenger.helpers.Converters;
import com.simplemobiletools.smsmessenger.models.Message;
import com.simplemobiletools.smsmessenger.models.MessageAttachment;
import com.simplemobiletools.smsmessenger.models.RecycleBinMessage;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MessagesDao_Impl implements MessagesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Message> __insertionAdapterOfMessage;

  private final Converters __converters = new Converters();

  private final EntityInsertionAdapter<RecycleBinMessage> __insertionAdapterOfRecycleBinMessage;

  private final EntityInsertionAdapter<Message> __insertionAdapterOfMessage_1;

  private final SharedSQLiteStatement __preparedStmtOfMarkRead;

  private final SharedSQLiteStatement __preparedStmtOfMarkThreadRead;

  private final SharedSQLiteStatement __preparedStmtOfUpdateType;

  private final SharedSQLiteStatement __preparedStmtOfUpdateStatus;

  private final SharedSQLiteStatement __preparedStmtOfDeleteFromMessages;

  private final SharedSQLiteStatement __preparedStmtOfDeleteFromRecycleBin;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllThreadMessages;

  private final SharedSQLiteStatement __preparedStmtOfDeleteThreadMessagesFromRecycleBin;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MessagesDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessage = new EntityInsertionAdapter<Message>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `messages` (`id`,`body`,`type`,`status`,`participants`,`date`,`read`,`thread_id`,`is_mms`,`attachment`,`sender_phone_number`,`sender_name`,`sender_photo_uri`,`subscription_id`,`is_scheduled`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Message entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getBody());
        statement.bindLong(3, entity.getType());
        statement.bindLong(4, entity.getStatus());
        final String _tmp = __converters.simpleContactListToJson(entity.getParticipants());
        statement.bindString(5, _tmp);
        statement.bindLong(6, entity.getDate());
        final int _tmp_1 = entity.getRead() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        statement.bindLong(8, entity.getThreadId());
        final int _tmp_2 = entity.isMMS() ? 1 : 0;
        statement.bindLong(9, _tmp_2);
        final String _tmp_3 = __converters.messageAttachmentToJson(entity.getAttachment());
        statement.bindString(10, _tmp_3);
        statement.bindString(11, entity.getSenderPhoneNumber());
        statement.bindString(12, entity.getSenderName());
        statement.bindString(13, entity.getSenderPhotoUri());
        statement.bindLong(14, entity.getSubscriptionId());
        final int _tmp_4 = entity.isScheduled() ? 1 : 0;
        statement.bindLong(15, _tmp_4);
      }
    };
    this.__insertionAdapterOfRecycleBinMessage = new EntityInsertionAdapter<RecycleBinMessage>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `recycle_bin_messages` (`id`,`deleted_ts`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RecycleBinMessage entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDeletedTS());
      }
    };
    this.__insertionAdapterOfMessage_1 = new EntityInsertionAdapter<Message>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `messages` (`id`,`body`,`type`,`status`,`participants`,`date`,`read`,`thread_id`,`is_mms`,`attachment`,`sender_phone_number`,`sender_name`,`sender_photo_uri`,`subscription_id`,`is_scheduled`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Message entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getBody());
        statement.bindLong(3, entity.getType());
        statement.bindLong(4, entity.getStatus());
        final String _tmp = __converters.simpleContactListToJson(entity.getParticipants());
        statement.bindString(5, _tmp);
        statement.bindLong(6, entity.getDate());
        final int _tmp_1 = entity.getRead() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        statement.bindLong(8, entity.getThreadId());
        final int _tmp_2 = entity.isMMS() ? 1 : 0;
        statement.bindLong(9, _tmp_2);
        final String _tmp_3 = __converters.messageAttachmentToJson(entity.getAttachment());
        statement.bindString(10, _tmp_3);
        statement.bindString(11, entity.getSenderPhoneNumber());
        statement.bindString(12, entity.getSenderName());
        statement.bindString(13, entity.getSenderPhotoUri());
        statement.bindLong(14, entity.getSubscriptionId());
        final int _tmp_4 = entity.isScheduled() ? 1 : 0;
        statement.bindLong(15, _tmp_4);
      }
    };
    this.__preparedStmtOfMarkRead = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE messages SET read = 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkThreadRead = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE messages SET read = 1 WHERE thread_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateType = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE messages SET type = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE messages SET status = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteFromMessages = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM messages WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteFromRecycleBin = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM recycle_bin_messages WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllThreadMessages = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM messages WHERE thread_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteThreadMessagesFromRecycleBin = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM recycle_bin_messages WHERE id IN (SELECT id FROM messages WHERE thread_id = ?)";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM messages";
        return _query;
      }
    };
  }

  @Override
  public void insertOrUpdate(final Message message) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage.insert(message);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertRecycleBinEntry(final RecycleBinMessage recycleBinMessage) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRecycleBinMessage.insert(recycleBinMessage);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long insertOrIgnore(final Message message) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfMessage_1.insertAndReturnId(message);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertMessages(final Message... message) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage.insert(message);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final long id) {
    __db.beginTransaction();
    try {
      MessagesDao.DefaultImpls.delete(MessagesDao_Impl.this, id);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteThreadMessages(final long threadId) {
    __db.beginTransaction();
    try {
      MessagesDao.DefaultImpls.deleteThreadMessages(MessagesDao_Impl.this, threadId);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void markRead(final long id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfMarkRead.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfMarkRead.release(_stmt);
    }
  }

  @Override
  public void markThreadRead(final long threadId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfMarkThreadRead.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, threadId);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfMarkThreadRead.release(_stmt);
    }
  }

  @Override
  public int updateType(final long id, final int type) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateType.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, type);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        final int _result = _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateType.release(_stmt);
    }
  }

  @Override
  public int updateStatus(final long id, final int status) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateStatus.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, status);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        final int _result = _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateStatus.release(_stmt);
    }
  }

  @Override
  public void deleteFromMessages(final long id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteFromMessages.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteFromMessages.release(_stmt);
    }
  }

  @Override
  public void deleteFromRecycleBin(final long id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteFromRecycleBin.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteFromRecycleBin.release(_stmt);
    }
  }

  @Override
  public void deleteAllThreadMessages(final long threadId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllThreadMessages.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, threadId);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAllThreadMessages.release(_stmt);
    }
  }

  @Override
  public void deleteThreadMessagesFromRecycleBin(final long threadId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteThreadMessagesFromRecycleBin.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, threadId);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteThreadMessagesFromRecycleBin.release(_stmt);
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Message> getAll() {
    final String _sql = "SELECT * FROM messages";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfParticipants = CursorUtil.getColumnIndexOrThrow(_cursor, "participants");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfIsMMS = CursorUtil.getColumnIndexOrThrow(_cursor, "is_mms");
      final int _cursorIndexOfAttachment = CursorUtil.getColumnIndexOrThrow(_cursor, "attachment");
      final int _cursorIndexOfSenderPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_phone_number");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_name");
      final int _cursorIndexOfSenderPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_photo_uri");
      final int _cursorIndexOfSubscriptionId = CursorUtil.getColumnIndexOrThrow(_cursor, "subscription_id");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Message _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpBody;
        _tmpBody = _cursor.getString(_cursorIndexOfBody);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        final int _tmpStatus;
        _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        final ArrayList<SimpleContact> _tmpParticipants;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfParticipants);
        _tmpParticipants = __converters.jsonToSimpleContactList(_tmp);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp_1 != 0;
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final boolean _tmpIsMMS;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsMMS);
        _tmpIsMMS = _tmp_2 != 0;
        final MessageAttachment _tmpAttachment;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfAttachment);
        _tmpAttachment = __converters.jsonToMessageAttachment(_tmp_3);
        final String _tmpSenderPhoneNumber;
        _tmpSenderPhoneNumber = _cursor.getString(_cursorIndexOfSenderPhoneNumber);
        final String _tmpSenderName;
        _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        final String _tmpSenderPhotoUri;
        _tmpSenderPhotoUri = _cursor.getString(_cursorIndexOfSenderPhotoUri);
        final int _tmpSubscriptionId;
        _tmpSubscriptionId = _cursor.getInt(_cursorIndexOfSubscriptionId);
        final boolean _tmpIsScheduled;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_4 != 0;
        _item = new Message(_tmpId,_tmpBody,_tmpType,_tmpStatus,_tmpParticipants,_tmpDate,_tmpRead,_tmpThreadId,_tmpIsMMS,_tmpAttachment,_tmpSenderPhoneNumber,_tmpSenderName,_tmpSenderPhotoUri,_tmpSubscriptionId,_tmpIsScheduled);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message> getAllRecycleBinMessages() {
    final String _sql = "SELECT messages.* FROM messages LEFT OUTER JOIN recycle_bin_messages ON messages.id = recycle_bin_messages.id WHERE recycle_bin_messages.id IS NOT NULL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfParticipants = CursorUtil.getColumnIndexOrThrow(_cursor, "participants");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfIsMMS = CursorUtil.getColumnIndexOrThrow(_cursor, "is_mms");
      final int _cursorIndexOfAttachment = CursorUtil.getColumnIndexOrThrow(_cursor, "attachment");
      final int _cursorIndexOfSenderPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_phone_number");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_name");
      final int _cursorIndexOfSenderPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_photo_uri");
      final int _cursorIndexOfSubscriptionId = CursorUtil.getColumnIndexOrThrow(_cursor, "subscription_id");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Message _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpBody;
        _tmpBody = _cursor.getString(_cursorIndexOfBody);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        final int _tmpStatus;
        _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        final ArrayList<SimpleContact> _tmpParticipants;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfParticipants);
        _tmpParticipants = __converters.jsonToSimpleContactList(_tmp);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp_1 != 0;
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final boolean _tmpIsMMS;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsMMS);
        _tmpIsMMS = _tmp_2 != 0;
        final MessageAttachment _tmpAttachment;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfAttachment);
        _tmpAttachment = __converters.jsonToMessageAttachment(_tmp_3);
        final String _tmpSenderPhoneNumber;
        _tmpSenderPhoneNumber = _cursor.getString(_cursorIndexOfSenderPhoneNumber);
        final String _tmpSenderName;
        _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        final String _tmpSenderPhotoUri;
        _tmpSenderPhotoUri = _cursor.getString(_cursorIndexOfSenderPhotoUri);
        final int _tmpSubscriptionId;
        _tmpSubscriptionId = _cursor.getInt(_cursorIndexOfSubscriptionId);
        final boolean _tmpIsScheduled;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_4 != 0;
        _item = new Message(_tmpId,_tmpBody,_tmpType,_tmpStatus,_tmpParticipants,_tmpDate,_tmpRead,_tmpThreadId,_tmpIsMMS,_tmpAttachment,_tmpSenderPhoneNumber,_tmpSenderName,_tmpSenderPhotoUri,_tmpSubscriptionId,_tmpIsScheduled);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message> getOldRecycleBinMessages(final long timestamp) {
    final String _sql = "SELECT messages.* FROM messages LEFT OUTER JOIN recycle_bin_messages ON messages.id = recycle_bin_messages.id WHERE recycle_bin_messages.id IS NOT NULL AND recycle_bin_messages.deleted_ts < ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, timestamp);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfParticipants = CursorUtil.getColumnIndexOrThrow(_cursor, "participants");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfIsMMS = CursorUtil.getColumnIndexOrThrow(_cursor, "is_mms");
      final int _cursorIndexOfAttachment = CursorUtil.getColumnIndexOrThrow(_cursor, "attachment");
      final int _cursorIndexOfSenderPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_phone_number");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_name");
      final int _cursorIndexOfSenderPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_photo_uri");
      final int _cursorIndexOfSubscriptionId = CursorUtil.getColumnIndexOrThrow(_cursor, "subscription_id");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Message _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpBody;
        _tmpBody = _cursor.getString(_cursorIndexOfBody);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        final int _tmpStatus;
        _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        final ArrayList<SimpleContact> _tmpParticipants;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfParticipants);
        _tmpParticipants = __converters.jsonToSimpleContactList(_tmp);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp_1 != 0;
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final boolean _tmpIsMMS;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsMMS);
        _tmpIsMMS = _tmp_2 != 0;
        final MessageAttachment _tmpAttachment;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfAttachment);
        _tmpAttachment = __converters.jsonToMessageAttachment(_tmp_3);
        final String _tmpSenderPhoneNumber;
        _tmpSenderPhoneNumber = _cursor.getString(_cursorIndexOfSenderPhoneNumber);
        final String _tmpSenderName;
        _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        final String _tmpSenderPhotoUri;
        _tmpSenderPhotoUri = _cursor.getString(_cursorIndexOfSenderPhotoUri);
        final int _tmpSubscriptionId;
        _tmpSubscriptionId = _cursor.getInt(_cursorIndexOfSubscriptionId);
        final boolean _tmpIsScheduled;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_4 != 0;
        _item = new Message(_tmpId,_tmpBody,_tmpType,_tmpStatus,_tmpParticipants,_tmpDate,_tmpRead,_tmpThreadId,_tmpIsMMS,_tmpAttachment,_tmpSenderPhoneNumber,_tmpSenderName,_tmpSenderPhotoUri,_tmpSubscriptionId,_tmpIsScheduled);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message> getThreadMessages(final long threadId) {
    final String _sql = "SELECT * FROM messages WHERE thread_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, threadId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfParticipants = CursorUtil.getColumnIndexOrThrow(_cursor, "participants");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfIsMMS = CursorUtil.getColumnIndexOrThrow(_cursor, "is_mms");
      final int _cursorIndexOfAttachment = CursorUtil.getColumnIndexOrThrow(_cursor, "attachment");
      final int _cursorIndexOfSenderPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_phone_number");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_name");
      final int _cursorIndexOfSenderPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_photo_uri");
      final int _cursorIndexOfSubscriptionId = CursorUtil.getColumnIndexOrThrow(_cursor, "subscription_id");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Message _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpBody;
        _tmpBody = _cursor.getString(_cursorIndexOfBody);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        final int _tmpStatus;
        _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        final ArrayList<SimpleContact> _tmpParticipants;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfParticipants);
        _tmpParticipants = __converters.jsonToSimpleContactList(_tmp);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp_1 != 0;
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final boolean _tmpIsMMS;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsMMS);
        _tmpIsMMS = _tmp_2 != 0;
        final MessageAttachment _tmpAttachment;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfAttachment);
        _tmpAttachment = __converters.jsonToMessageAttachment(_tmp_3);
        final String _tmpSenderPhoneNumber;
        _tmpSenderPhoneNumber = _cursor.getString(_cursorIndexOfSenderPhoneNumber);
        final String _tmpSenderName;
        _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        final String _tmpSenderPhotoUri;
        _tmpSenderPhotoUri = _cursor.getString(_cursorIndexOfSenderPhotoUri);
        final int _tmpSubscriptionId;
        _tmpSubscriptionId = _cursor.getInt(_cursorIndexOfSubscriptionId);
        final boolean _tmpIsScheduled;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_4 != 0;
        _item = new Message(_tmpId,_tmpBody,_tmpType,_tmpStatus,_tmpParticipants,_tmpDate,_tmpRead,_tmpThreadId,_tmpIsMMS,_tmpAttachment,_tmpSenderPhoneNumber,_tmpSenderName,_tmpSenderPhotoUri,_tmpSubscriptionId,_tmpIsScheduled);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message> getNonRecycledThreadMessages(final long threadId) {
    final String _sql = "SELECT messages.* FROM messages LEFT OUTER JOIN recycle_bin_messages ON messages.id = recycle_bin_messages.id WHERE recycle_bin_messages.id IS NULL AND thread_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, threadId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfParticipants = CursorUtil.getColumnIndexOrThrow(_cursor, "participants");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfIsMMS = CursorUtil.getColumnIndexOrThrow(_cursor, "is_mms");
      final int _cursorIndexOfAttachment = CursorUtil.getColumnIndexOrThrow(_cursor, "attachment");
      final int _cursorIndexOfSenderPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_phone_number");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_name");
      final int _cursorIndexOfSenderPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_photo_uri");
      final int _cursorIndexOfSubscriptionId = CursorUtil.getColumnIndexOrThrow(_cursor, "subscription_id");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Message _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpBody;
        _tmpBody = _cursor.getString(_cursorIndexOfBody);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        final int _tmpStatus;
        _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        final ArrayList<SimpleContact> _tmpParticipants;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfParticipants);
        _tmpParticipants = __converters.jsonToSimpleContactList(_tmp);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp_1 != 0;
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final boolean _tmpIsMMS;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsMMS);
        _tmpIsMMS = _tmp_2 != 0;
        final MessageAttachment _tmpAttachment;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfAttachment);
        _tmpAttachment = __converters.jsonToMessageAttachment(_tmp_3);
        final String _tmpSenderPhoneNumber;
        _tmpSenderPhoneNumber = _cursor.getString(_cursorIndexOfSenderPhoneNumber);
        final String _tmpSenderName;
        _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        final String _tmpSenderPhotoUri;
        _tmpSenderPhotoUri = _cursor.getString(_cursorIndexOfSenderPhotoUri);
        final int _tmpSubscriptionId;
        _tmpSubscriptionId = _cursor.getInt(_cursorIndexOfSubscriptionId);
        final boolean _tmpIsScheduled;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_4 != 0;
        _item = new Message(_tmpId,_tmpBody,_tmpType,_tmpStatus,_tmpParticipants,_tmpDate,_tmpRead,_tmpThreadId,_tmpIsMMS,_tmpAttachment,_tmpSenderPhoneNumber,_tmpSenderName,_tmpSenderPhotoUri,_tmpSubscriptionId,_tmpIsScheduled);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message> getThreadMessagesFromRecycleBin(final long threadId) {
    final String _sql = "SELECT messages.* FROM messages LEFT OUTER JOIN recycle_bin_messages ON messages.id = recycle_bin_messages.id WHERE recycle_bin_messages.id IS NOT NULL AND thread_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, threadId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfParticipants = CursorUtil.getColumnIndexOrThrow(_cursor, "participants");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfIsMMS = CursorUtil.getColumnIndexOrThrow(_cursor, "is_mms");
      final int _cursorIndexOfAttachment = CursorUtil.getColumnIndexOrThrow(_cursor, "attachment");
      final int _cursorIndexOfSenderPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_phone_number");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_name");
      final int _cursorIndexOfSenderPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_photo_uri");
      final int _cursorIndexOfSubscriptionId = CursorUtil.getColumnIndexOrThrow(_cursor, "subscription_id");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Message _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpBody;
        _tmpBody = _cursor.getString(_cursorIndexOfBody);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        final int _tmpStatus;
        _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        final ArrayList<SimpleContact> _tmpParticipants;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfParticipants);
        _tmpParticipants = __converters.jsonToSimpleContactList(_tmp);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp_1 != 0;
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final boolean _tmpIsMMS;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsMMS);
        _tmpIsMMS = _tmp_2 != 0;
        final MessageAttachment _tmpAttachment;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfAttachment);
        _tmpAttachment = __converters.jsonToMessageAttachment(_tmp_3);
        final String _tmpSenderPhoneNumber;
        _tmpSenderPhoneNumber = _cursor.getString(_cursorIndexOfSenderPhoneNumber);
        final String _tmpSenderName;
        _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        final String _tmpSenderPhotoUri;
        _tmpSenderPhotoUri = _cursor.getString(_cursorIndexOfSenderPhotoUri);
        final int _tmpSubscriptionId;
        _tmpSubscriptionId = _cursor.getInt(_cursorIndexOfSubscriptionId);
        final boolean _tmpIsScheduled;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_4 != 0;
        _item = new Message(_tmpId,_tmpBody,_tmpType,_tmpStatus,_tmpParticipants,_tmpDate,_tmpRead,_tmpThreadId,_tmpIsMMS,_tmpAttachment,_tmpSenderPhoneNumber,_tmpSenderName,_tmpSenderPhotoUri,_tmpSubscriptionId,_tmpIsScheduled);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message> getScheduledThreadMessages(final long threadId) {
    final String _sql = "SELECT messages.* FROM messages LEFT OUTER JOIN recycle_bin_messages ON messages.id = recycle_bin_messages.id WHERE recycle_bin_messages.id IS NULL AND thread_id = ? AND is_scheduled = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, threadId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfParticipants = CursorUtil.getColumnIndexOrThrow(_cursor, "participants");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfIsMMS = CursorUtil.getColumnIndexOrThrow(_cursor, "is_mms");
      final int _cursorIndexOfAttachment = CursorUtil.getColumnIndexOrThrow(_cursor, "attachment");
      final int _cursorIndexOfSenderPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_phone_number");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_name");
      final int _cursorIndexOfSenderPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_photo_uri");
      final int _cursorIndexOfSubscriptionId = CursorUtil.getColumnIndexOrThrow(_cursor, "subscription_id");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Message _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpBody;
        _tmpBody = _cursor.getString(_cursorIndexOfBody);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        final int _tmpStatus;
        _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        final ArrayList<SimpleContact> _tmpParticipants;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfParticipants);
        _tmpParticipants = __converters.jsonToSimpleContactList(_tmp);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp_1 != 0;
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final boolean _tmpIsMMS;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsMMS);
        _tmpIsMMS = _tmp_2 != 0;
        final MessageAttachment _tmpAttachment;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfAttachment);
        _tmpAttachment = __converters.jsonToMessageAttachment(_tmp_3);
        final String _tmpSenderPhoneNumber;
        _tmpSenderPhoneNumber = _cursor.getString(_cursorIndexOfSenderPhoneNumber);
        final String _tmpSenderName;
        _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        final String _tmpSenderPhotoUri;
        _tmpSenderPhotoUri = _cursor.getString(_cursorIndexOfSenderPhotoUri);
        final int _tmpSubscriptionId;
        _tmpSubscriptionId = _cursor.getInt(_cursorIndexOfSubscriptionId);
        final boolean _tmpIsScheduled;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_4 != 0;
        _item = new Message(_tmpId,_tmpBody,_tmpType,_tmpStatus,_tmpParticipants,_tmpDate,_tmpRead,_tmpThreadId,_tmpIsMMS,_tmpAttachment,_tmpSenderPhoneNumber,_tmpSenderName,_tmpSenderPhotoUri,_tmpSubscriptionId,_tmpIsScheduled);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Message getScheduledMessageWithId(final long threadId, final long messageId) {
    final String _sql = "SELECT * FROM messages WHERE thread_id = ? AND id = ? AND is_scheduled = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, threadId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, messageId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfParticipants = CursorUtil.getColumnIndexOrThrow(_cursor, "participants");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfIsMMS = CursorUtil.getColumnIndexOrThrow(_cursor, "is_mms");
      final int _cursorIndexOfAttachment = CursorUtil.getColumnIndexOrThrow(_cursor, "attachment");
      final int _cursorIndexOfSenderPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_phone_number");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_name");
      final int _cursorIndexOfSenderPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_photo_uri");
      final int _cursorIndexOfSubscriptionId = CursorUtil.getColumnIndexOrThrow(_cursor, "subscription_id");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final Message _result;
      if (_cursor.moveToFirst()) {
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpBody;
        _tmpBody = _cursor.getString(_cursorIndexOfBody);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        final int _tmpStatus;
        _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        final ArrayList<SimpleContact> _tmpParticipants;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfParticipants);
        _tmpParticipants = __converters.jsonToSimpleContactList(_tmp);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp_1 != 0;
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final boolean _tmpIsMMS;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsMMS);
        _tmpIsMMS = _tmp_2 != 0;
        final MessageAttachment _tmpAttachment;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfAttachment);
        _tmpAttachment = __converters.jsonToMessageAttachment(_tmp_3);
        final String _tmpSenderPhoneNumber;
        _tmpSenderPhoneNumber = _cursor.getString(_cursorIndexOfSenderPhoneNumber);
        final String _tmpSenderName;
        _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        final String _tmpSenderPhotoUri;
        _tmpSenderPhotoUri = _cursor.getString(_cursorIndexOfSenderPhotoUri);
        final int _tmpSubscriptionId;
        _tmpSubscriptionId = _cursor.getInt(_cursorIndexOfSubscriptionId);
        final boolean _tmpIsScheduled;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_4 != 0;
        _result = new Message(_tmpId,_tmpBody,_tmpType,_tmpStatus,_tmpParticipants,_tmpDate,_tmpRead,_tmpThreadId,_tmpIsMMS,_tmpAttachment,_tmpSenderPhoneNumber,_tmpSenderName,_tmpSenderPhotoUri,_tmpSubscriptionId,_tmpIsScheduled);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getArchivedCount() {
    final String _sql = "SELECT COUNT(*) FROM recycle_bin_messages";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message> getMessagesWithText(final String text) {
    final String _sql = "SELECT * FROM messages WHERE body LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, text);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfParticipants = CursorUtil.getColumnIndexOrThrow(_cursor, "participants");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfIsMMS = CursorUtil.getColumnIndexOrThrow(_cursor, "is_mms");
      final int _cursorIndexOfAttachment = CursorUtil.getColumnIndexOrThrow(_cursor, "attachment");
      final int _cursorIndexOfSenderPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_phone_number");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_name");
      final int _cursorIndexOfSenderPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_photo_uri");
      final int _cursorIndexOfSubscriptionId = CursorUtil.getColumnIndexOrThrow(_cursor, "subscription_id");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Message _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpBody;
        _tmpBody = _cursor.getString(_cursorIndexOfBody);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        final int _tmpStatus;
        _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        final ArrayList<SimpleContact> _tmpParticipants;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfParticipants);
        _tmpParticipants = __converters.jsonToSimpleContactList(_tmp);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp_1 != 0;
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final boolean _tmpIsMMS;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsMMS);
        _tmpIsMMS = _tmp_2 != 0;
        final MessageAttachment _tmpAttachment;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfAttachment);
        _tmpAttachment = __converters.jsonToMessageAttachment(_tmp_3);
        final String _tmpSenderPhoneNumber;
        _tmpSenderPhoneNumber = _cursor.getString(_cursorIndexOfSenderPhoneNumber);
        final String _tmpSenderName;
        _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        final String _tmpSenderPhotoUri;
        _tmpSenderPhotoUri = _cursor.getString(_cursorIndexOfSenderPhotoUri);
        final int _tmpSubscriptionId;
        _tmpSubscriptionId = _cursor.getInt(_cursorIndexOfSubscriptionId);
        final boolean _tmpIsScheduled;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_4 != 0;
        _item = new Message(_tmpId,_tmpBody,_tmpType,_tmpStatus,_tmpParticipants,_tmpDate,_tmpRead,_tmpThreadId,_tmpIsMMS,_tmpAttachment,_tmpSenderPhoneNumber,_tmpSenderName,_tmpSenderPhotoUri,_tmpSubscriptionId,_tmpIsScheduled);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
