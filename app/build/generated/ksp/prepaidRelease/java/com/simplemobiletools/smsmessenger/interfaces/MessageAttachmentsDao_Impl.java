package com.simplemobiletools.smsmessenger.interfaces;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import com.simplemobiletools.smsmessenger.helpers.Converters;
import com.simplemobiletools.smsmessenger.models.Attachment;
import com.simplemobiletools.smsmessenger.models.MessageAttachment;
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
public final class MessageAttachmentsDao_Impl implements MessageAttachmentsDao {
  private final RoomDatabase __db;

  private final Converters __converters = new Converters();

  public MessageAttachmentsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public List<MessageAttachment> getAll() {
    final String _sql = "SELECT * FROM message_attachments";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
      final int _cursorIndexOfAttachments = CursorUtil.getColumnIndexOrThrow(_cursor, "attachments");
      final List<MessageAttachment> _result = new ArrayList<MessageAttachment>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final MessageAttachment _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpText;
        _tmpText = _cursor.getString(_cursorIndexOfText);
        final ArrayList<Attachment> _tmpAttachments;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfAttachments);
        _tmpAttachments = __converters.jsonToAttachmentList(_tmp);
        _item = new MessageAttachment(_tmpId,_tmpText,_tmpAttachments);
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
