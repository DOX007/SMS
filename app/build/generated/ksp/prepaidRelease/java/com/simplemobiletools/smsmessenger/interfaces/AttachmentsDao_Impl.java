package com.simplemobiletools.smsmessenger.interfaces;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import com.simplemobiletools.smsmessenger.models.Attachment;
import java.lang.Class;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AttachmentsDao_Impl implements AttachmentsDao {
  private final RoomDatabase __db;

  public AttachmentsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public List<Attachment> getAll() {
    final String _sql = "SELECT * FROM attachments";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfMessageId = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id");
      final int _cursorIndexOfUriString = CursorUtil.getColumnIndexOrThrow(_cursor, "uri_string");
      final int _cursorIndexOfMimetype = CursorUtil.getColumnIndexOrThrow(_cursor, "mimetype");
      final int _cursorIndexOfWidth = CursorUtil.getColumnIndexOrThrow(_cursor, "width");
      final int _cursorIndexOfHeight = CursorUtil.getColumnIndexOrThrow(_cursor, "height");
      final int _cursorIndexOfFilename = CursorUtil.getColumnIndexOrThrow(_cursor, "filename");
      final List<Attachment> _result = new ArrayList<Attachment>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Attachment _item;
        final Long _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getLong(_cursorIndexOfId);
        }
        final long _tmpMessageId;
        _tmpMessageId = _cursor.getLong(_cursorIndexOfMessageId);
        final String _tmpUriString;
        _tmpUriString = _cursor.getString(_cursorIndexOfUriString);
        final String _tmpMimetype;
        _tmpMimetype = _cursor.getString(_cursorIndexOfMimetype);
        final int _tmpWidth;
        _tmpWidth = _cursor.getInt(_cursorIndexOfWidth);
        final int _tmpHeight;
        _tmpHeight = _cursor.getInt(_cursorIndexOfHeight);
        final String _tmpFilename;
        _tmpFilename = _cursor.getString(_cursorIndexOfFilename);
        _item = new Attachment(_tmpId,_tmpMessageId,_tmpUriString,_tmpMimetype,_tmpWidth,_tmpHeight,_tmpFilename);
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
