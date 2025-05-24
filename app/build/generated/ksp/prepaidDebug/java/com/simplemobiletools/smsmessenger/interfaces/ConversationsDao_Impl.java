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
import com.simplemobiletools.smsmessenger.models.Conversation;
import com.simplemobiletools.smsmessenger.models.ConversationWithSnippetOverride;
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
public final class ConversationsDao_Impl implements ConversationsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Conversation> __insertionAdapterOfConversation;

  private final SharedSQLiteStatement __preparedStmtOfMarkRead;

  private final SharedSQLiteStatement __preparedStmtOfMarkUnread;

  private final SharedSQLiteStatement __preparedStmtOfMoveToArchive;

  private final SharedSQLiteStatement __preparedStmtOfUnarchive;

  private final SharedSQLiteStatement __preparedStmtOfDeleteThreadId;

  public ConversationsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfConversation = new EntityInsertionAdapter<Conversation>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `conversations` (`thread_id`,`snippet`,`date`,`read`,`title`,`photo_uri`,`is_group_conversation`,`phone_number`,`is_scheduled`,`uses_custom_title`,`archived`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Conversation entity) {
        statement.bindLong(1, entity.getThreadId());
        statement.bindString(2, entity.getSnippet());
        statement.bindLong(3, entity.getDate());
        final int _tmp = entity.getRead() ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindString(5, entity.getTitle());
        statement.bindString(6, entity.getPhotoUri());
        final int _tmp_1 = entity.isGroupConversation() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        statement.bindString(8, entity.getPhoneNumber());
        final int _tmp_2 = entity.isScheduled() ? 1 : 0;
        statement.bindLong(9, _tmp_2);
        final int _tmp_3 = entity.getUsesCustomTitle() ? 1 : 0;
        statement.bindLong(10, _tmp_3);
        final int _tmp_4 = entity.isArchived() ? 1 : 0;
        statement.bindLong(11, _tmp_4);
      }
    };
    this.__preparedStmtOfMarkRead = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE conversations SET read = 1 WHERE thread_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkUnread = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE conversations SET read = 0 WHERE thread_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMoveToArchive = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE conversations SET archived = 1 WHERE thread_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUnarchive = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE conversations SET archived = 0 WHERE thread_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteThreadId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM conversations WHERE thread_id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insertOrUpdate(final Conversation conversation) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfConversation.insertAndReturnId(conversation);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void markRead(final long threadId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfMarkRead.acquire();
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
      __preparedStmtOfMarkRead.release(_stmt);
    }
  }

  @Override
  public void markUnread(final long threadId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfMarkUnread.acquire();
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
      __preparedStmtOfMarkUnread.release(_stmt);
    }
  }

  @Override
  public void moveToArchive(final long threadId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfMoveToArchive.acquire();
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
      __preparedStmtOfMoveToArchive.release(_stmt);
    }
  }

  @Override
  public void unarchive(final long threadId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUnarchive.acquire();
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
      __preparedStmtOfUnarchive.release(_stmt);
    }
  }

  @Override
  public void deleteThreadId(final long threadId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteThreadId.acquire();
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
      __preparedStmtOfDeleteThreadId.release(_stmt);
    }
  }

  @Override
  public List<ConversationWithSnippetOverride> getNonArchivedWithLatestSnippet() {
    final String _sql = "SELECT (SELECT body FROM messages LEFT OUTER JOIN recycle_bin_messages ON messages.id = recycle_bin_messages.id WHERE recycle_bin_messages.id IS NULL AND messages.thread_id = conversations.thread_id ORDER BY messages.date DESC LIMIT 1) as new_snippet, * FROM conversations WHERE archived = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSnippet = CursorUtil.getColumnIndexOrThrow(_cursor, "new_snippet");
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfSnippet_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "snippet");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "photo_uri");
      final int _cursorIndexOfIsGroupConversation = CursorUtil.getColumnIndexOrThrow(_cursor, "is_group_conversation");
      final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_number");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final int _cursorIndexOfUsesCustomTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "uses_custom_title");
      final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "archived");
      final List<ConversationWithSnippetOverride> _result = new ArrayList<ConversationWithSnippetOverride>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final ConversationWithSnippetOverride _item;
        final String _tmpSnippet;
        if (_cursor.isNull(_cursorIndexOfSnippet)) {
          _tmpSnippet = null;
        } else {
          _tmpSnippet = _cursor.getString(_cursorIndexOfSnippet);
        }
        final Conversation _tmpConversation;
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final String _tmpSnippet_1;
        _tmpSnippet_1 = _cursor.getString(_cursorIndexOfSnippet_1);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp != 0;
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpPhotoUri;
        _tmpPhotoUri = _cursor.getString(_cursorIndexOfPhotoUri);
        final boolean _tmpIsGroupConversation;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsGroupConversation);
        _tmpIsGroupConversation = _tmp_1 != 0;
        final String _tmpPhoneNumber;
        _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
        final boolean _tmpIsScheduled;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_2 != 0;
        final boolean _tmpUsesCustomTitle;
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfUsesCustomTitle);
        _tmpUsesCustomTitle = _tmp_3 != 0;
        final boolean _tmpIsArchived;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsArchived);
        _tmpIsArchived = _tmp_4 != 0;
        _tmpConversation = new Conversation(_tmpThreadId,_tmpSnippet_1,_tmpDate,_tmpRead,_tmpTitle,_tmpPhotoUri,_tmpIsGroupConversation,_tmpPhoneNumber,_tmpIsScheduled,_tmpUsesCustomTitle,_tmpIsArchived);
        _item = new ConversationWithSnippetOverride(_tmpSnippet,_tmpConversation);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<ConversationWithSnippetOverride> getAllArchivedWithLatestSnippet() {
    final String _sql = "SELECT (SELECT body FROM messages LEFT OUTER JOIN recycle_bin_messages ON messages.id = recycle_bin_messages.id WHERE recycle_bin_messages.id IS NULL AND messages.thread_id = conversations.thread_id ORDER BY messages.date DESC LIMIT 1) as new_snippet, * FROM conversations WHERE archived = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSnippet = CursorUtil.getColumnIndexOrThrow(_cursor, "new_snippet");
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfSnippet_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "snippet");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "photo_uri");
      final int _cursorIndexOfIsGroupConversation = CursorUtil.getColumnIndexOrThrow(_cursor, "is_group_conversation");
      final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_number");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final int _cursorIndexOfUsesCustomTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "uses_custom_title");
      final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "archived");
      final List<ConversationWithSnippetOverride> _result = new ArrayList<ConversationWithSnippetOverride>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final ConversationWithSnippetOverride _item;
        final String _tmpSnippet;
        if (_cursor.isNull(_cursorIndexOfSnippet)) {
          _tmpSnippet = null;
        } else {
          _tmpSnippet = _cursor.getString(_cursorIndexOfSnippet);
        }
        final Conversation _tmpConversation;
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final String _tmpSnippet_1;
        _tmpSnippet_1 = _cursor.getString(_cursorIndexOfSnippet_1);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp != 0;
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpPhotoUri;
        _tmpPhotoUri = _cursor.getString(_cursorIndexOfPhotoUri);
        final boolean _tmpIsGroupConversation;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsGroupConversation);
        _tmpIsGroupConversation = _tmp_1 != 0;
        final String _tmpPhoneNumber;
        _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
        final boolean _tmpIsScheduled;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_2 != 0;
        final boolean _tmpUsesCustomTitle;
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfUsesCustomTitle);
        _tmpUsesCustomTitle = _tmp_3 != 0;
        final boolean _tmpIsArchived;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsArchived);
        _tmpIsArchived = _tmp_4 != 0;
        _tmpConversation = new Conversation(_tmpThreadId,_tmpSnippet_1,_tmpDate,_tmpRead,_tmpTitle,_tmpPhotoUri,_tmpIsGroupConversation,_tmpPhoneNumber,_tmpIsScheduled,_tmpUsesCustomTitle,_tmpIsArchived);
        _item = new ConversationWithSnippetOverride(_tmpSnippet,_tmpConversation);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<ConversationWithSnippetOverride> getAllWithMessagesInRecycleBinWithLatestSnippet() {
    final String _sql = "SELECT (SELECT body FROM messages LEFT OUTER JOIN recycle_bin_messages ON messages.id = recycle_bin_messages.id WHERE recycle_bin_messages.id IS NOT NULL AND messages.thread_id = conversations.thread_id ORDER BY messages.date DESC LIMIT 1) as new_snippet, * FROM conversations WHERE (SELECT COUNT(*) FROM messages LEFT OUTER JOIN recycle_bin_messages ON messages.id = recycle_bin_messages.id WHERE recycle_bin_messages.id IS NOT NULL AND messages.thread_id = conversations.thread_id) > 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSnippet = CursorUtil.getColumnIndexOrThrow(_cursor, "new_snippet");
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfSnippet_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "snippet");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "photo_uri");
      final int _cursorIndexOfIsGroupConversation = CursorUtil.getColumnIndexOrThrow(_cursor, "is_group_conversation");
      final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_number");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final int _cursorIndexOfUsesCustomTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "uses_custom_title");
      final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "archived");
      final List<ConversationWithSnippetOverride> _result = new ArrayList<ConversationWithSnippetOverride>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final ConversationWithSnippetOverride _item;
        final String _tmpSnippet;
        if (_cursor.isNull(_cursorIndexOfSnippet)) {
          _tmpSnippet = null;
        } else {
          _tmpSnippet = _cursor.getString(_cursorIndexOfSnippet);
        }
        final Conversation _tmpConversation;
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final String _tmpSnippet_1;
        _tmpSnippet_1 = _cursor.getString(_cursorIndexOfSnippet_1);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp != 0;
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpPhotoUri;
        _tmpPhotoUri = _cursor.getString(_cursorIndexOfPhotoUri);
        final boolean _tmpIsGroupConversation;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsGroupConversation);
        _tmpIsGroupConversation = _tmp_1 != 0;
        final String _tmpPhoneNumber;
        _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
        final boolean _tmpIsScheduled;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_2 != 0;
        final boolean _tmpUsesCustomTitle;
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfUsesCustomTitle);
        _tmpUsesCustomTitle = _tmp_3 != 0;
        final boolean _tmpIsArchived;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsArchived);
        _tmpIsArchived = _tmp_4 != 0;
        _tmpConversation = new Conversation(_tmpThreadId,_tmpSnippet_1,_tmpDate,_tmpRead,_tmpTitle,_tmpPhotoUri,_tmpIsGroupConversation,_tmpPhoneNumber,_tmpIsScheduled,_tmpUsesCustomTitle,_tmpIsArchived);
        _item = new ConversationWithSnippetOverride(_tmpSnippet,_tmpConversation);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Conversation getConversationWithThreadId(final long threadId) {
    final String _sql = "SELECT * FROM conversations WHERE thread_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, threadId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfSnippet = CursorUtil.getColumnIndexOrThrow(_cursor, "snippet");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "photo_uri");
      final int _cursorIndexOfIsGroupConversation = CursorUtil.getColumnIndexOrThrow(_cursor, "is_group_conversation");
      final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_number");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final int _cursorIndexOfUsesCustomTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "uses_custom_title");
      final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "archived");
      final Conversation _result;
      if (_cursor.moveToFirst()) {
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final String _tmpSnippet;
        _tmpSnippet = _cursor.getString(_cursorIndexOfSnippet);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp != 0;
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpPhotoUri;
        _tmpPhotoUri = _cursor.getString(_cursorIndexOfPhotoUri);
        final boolean _tmpIsGroupConversation;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsGroupConversation);
        _tmpIsGroupConversation = _tmp_1 != 0;
        final String _tmpPhoneNumber;
        _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
        final boolean _tmpIsScheduled;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_2 != 0;
        final boolean _tmpUsesCustomTitle;
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfUsesCustomTitle);
        _tmpUsesCustomTitle = _tmp_3 != 0;
        final boolean _tmpIsArchived;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsArchived);
        _tmpIsArchived = _tmp_4 != 0;
        _result = new Conversation(_tmpThreadId,_tmpSnippet,_tmpDate,_tmpRead,_tmpTitle,_tmpPhotoUri,_tmpIsGroupConversation,_tmpPhoneNumber,_tmpIsScheduled,_tmpUsesCustomTitle,_tmpIsArchived);
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
  public List<Conversation> getUnreadConversations() {
    final String _sql = "SELECT * FROM conversations WHERE read = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfSnippet = CursorUtil.getColumnIndexOrThrow(_cursor, "snippet");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "photo_uri");
      final int _cursorIndexOfIsGroupConversation = CursorUtil.getColumnIndexOrThrow(_cursor, "is_group_conversation");
      final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_number");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final int _cursorIndexOfUsesCustomTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "uses_custom_title");
      final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "archived");
      final List<Conversation> _result = new ArrayList<Conversation>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Conversation _item;
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final String _tmpSnippet;
        _tmpSnippet = _cursor.getString(_cursorIndexOfSnippet);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp != 0;
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpPhotoUri;
        _tmpPhotoUri = _cursor.getString(_cursorIndexOfPhotoUri);
        final boolean _tmpIsGroupConversation;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsGroupConversation);
        _tmpIsGroupConversation = _tmp_1 != 0;
        final String _tmpPhoneNumber;
        _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
        final boolean _tmpIsScheduled;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_2 != 0;
        final boolean _tmpUsesCustomTitle;
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfUsesCustomTitle);
        _tmpUsesCustomTitle = _tmp_3 != 0;
        final boolean _tmpIsArchived;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsArchived);
        _tmpIsArchived = _tmp_4 != 0;
        _item = new Conversation(_tmpThreadId,_tmpSnippet,_tmpDate,_tmpRead,_tmpTitle,_tmpPhotoUri,_tmpIsGroupConversation,_tmpPhoneNumber,_tmpIsScheduled,_tmpUsesCustomTitle,_tmpIsArchived);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Conversation> getConversationsWithText(final String text) {
    final String _sql = "SELECT * FROM conversations WHERE title LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, text);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfThreadId = CursorUtil.getColumnIndexOrThrow(_cursor, "thread_id");
      final int _cursorIndexOfSnippet = CursorUtil.getColumnIndexOrThrow(_cursor, "snippet");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "photo_uri");
      final int _cursorIndexOfIsGroupConversation = CursorUtil.getColumnIndexOrThrow(_cursor, "is_group_conversation");
      final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_number");
      final int _cursorIndexOfIsScheduled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_scheduled");
      final int _cursorIndexOfUsesCustomTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "uses_custom_title");
      final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "archived");
      final List<Conversation> _result = new ArrayList<Conversation>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Conversation _item;
        final long _tmpThreadId;
        _tmpThreadId = _cursor.getLong(_cursorIndexOfThreadId);
        final String _tmpSnippet;
        _tmpSnippet = _cursor.getString(_cursorIndexOfSnippet);
        final int _tmpDate;
        _tmpDate = _cursor.getInt(_cursorIndexOfDate);
        final boolean _tmpRead;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp != 0;
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpPhotoUri;
        _tmpPhotoUri = _cursor.getString(_cursorIndexOfPhotoUri);
        final boolean _tmpIsGroupConversation;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsGroupConversation);
        _tmpIsGroupConversation = _tmp_1 != 0;
        final String _tmpPhoneNumber;
        _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
        final boolean _tmpIsScheduled;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsScheduled);
        _tmpIsScheduled = _tmp_2 != 0;
        final boolean _tmpUsesCustomTitle;
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfUsesCustomTitle);
        _tmpUsesCustomTitle = _tmp_3 != 0;
        final boolean _tmpIsArchived;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsArchived);
        _tmpIsArchived = _tmp_4 != 0;
        _item = new Conversation(_tmpThreadId,_tmpSnippet,_tmpDate,_tmpRead,_tmpTitle,_tmpPhotoUri,_tmpIsGroupConversation,_tmpPhoneNumber,_tmpIsScheduled,_tmpUsesCustomTitle,_tmpIsArchived);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Conversation> getNonArchived() {
    return ConversationsDao.DefaultImpls.getNonArchived(ConversationsDao_Impl.this);
  }

  @Override
  public List<Conversation> getAllArchived() {
    return ConversationsDao.DefaultImpls.getAllArchived(ConversationsDao_Impl.this);
  }

  @Override
  public List<Conversation> getAllWithMessagesInRecycleBin() {
    return ConversationsDao.DefaultImpls.getAllWithMessagesInRecycleBin(ConversationsDao_Impl.this);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
