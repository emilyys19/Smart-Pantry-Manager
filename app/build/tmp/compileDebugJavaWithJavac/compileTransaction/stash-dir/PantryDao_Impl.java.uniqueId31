package com.smartpantrymanager.database;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PantryDao_Impl implements PantryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PantryItem> __insertionAdapterOfPantryItem;

  private final EntityDeletionOrUpdateAdapter<PantryItem> __deletionAdapterOfPantryItem;

  private final EntityDeletionOrUpdateAdapter<PantryItem> __updateAdapterOfPantryItem;

  private final SharedSQLiteStatement __preparedStmtOfPurgeAllVaultEntries;

  public PantryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPantryItem = new EntityInsertionAdapter<PantryItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `pantry_items` (`id`,`name`,`amount`,`unit`,`expiryDate`,`createdTimestamp`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final PantryItem entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        statement.bindDouble(3, entity.getAmount());
        if (entity.getUnit() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getUnit());
        }
        if (entity.getExpiryDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getExpiryDate());
        }
        statement.bindLong(6, entity.getCreatedTimestamp());
      }
    };
    this.__deletionAdapterOfPantryItem = new EntityDeletionOrUpdateAdapter<PantryItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `pantry_items` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final PantryItem entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfPantryItem = new EntityDeletionOrUpdateAdapter<PantryItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `pantry_items` SET `id` = ?,`name` = ?,`amount` = ?,`unit` = ?,`expiryDate` = ?,`createdTimestamp` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final PantryItem entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        statement.bindDouble(3, entity.getAmount());
        if (entity.getUnit() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getUnit());
        }
        if (entity.getExpiryDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getExpiryDate());
        }
        statement.bindLong(6, entity.getCreatedTimestamp());
        statement.bindLong(7, entity.getId());
      }
    };
    this.__preparedStmtOfPurgeAllVaultEntries = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM pantry_items";
        return _query;
      }
    };
  }

  @Override
  public long insertPantryVaultEntry(final PantryItem item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfPantryItem.insertAndReturnId(item);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deletePantryVaultEntry(final PantryItem item) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total += __deletionAdapterOfPantryItem.handle(item);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int updatePantryVaultEntry(final PantryItem item) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total += __updateAdapterOfPantryItem.handle(item);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void purgeAllVaultEntries() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfPurgeAllVaultEntries.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfPurgeAllVaultEntries.release(_stmt);
    }
  }

  @Override
  public List<PantryItem> fetchAllVaultEntries() {
    final String _sql = "SELECT * FROM pantry_items ORDER BY createdTimestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
      final int _cursorIndexOfExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expiryDate");
      final int _cursorIndexOfCreatedTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "createdTimestamp");
      final List<PantryItem> _result = new ArrayList<PantryItem>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final PantryItem _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        final String _tmpUnit;
        if (_cursor.isNull(_cursorIndexOfUnit)) {
          _tmpUnit = null;
        } else {
          _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
        }
        final String _tmpExpiryDate;
        if (_cursor.isNull(_cursorIndexOfExpiryDate)) {
          _tmpExpiryDate = null;
        } else {
          _tmpExpiryDate = _cursor.getString(_cursorIndexOfExpiryDate);
        }
        final long _tmpCreatedTimestamp;
        _tmpCreatedTimestamp = _cursor.getLong(_cursorIndexOfCreatedTimestamp);
        _item = new PantryItem(_tmpName,_tmpAmount,_tmpUnit,_tmpExpiryDate,_tmpCreatedTimestamp);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public PantryItem fetchVaultEntryById(final int id) {
    final String _sql = "SELECT * FROM pantry_items WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
      final int _cursorIndexOfExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expiryDate");
      final int _cursorIndexOfCreatedTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "createdTimestamp");
      final PantryItem _result;
      if (_cursor.moveToFirst()) {
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        final String _tmpUnit;
        if (_cursor.isNull(_cursorIndexOfUnit)) {
          _tmpUnit = null;
        } else {
          _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
        }
        final String _tmpExpiryDate;
        if (_cursor.isNull(_cursorIndexOfExpiryDate)) {
          _tmpExpiryDate = null;
        } else {
          _tmpExpiryDate = _cursor.getString(_cursorIndexOfExpiryDate);
        }
        final long _tmpCreatedTimestamp;
        _tmpCreatedTimestamp = _cursor.getLong(_cursorIndexOfCreatedTimestamp);
        _result = new PantryItem(_tmpName,_tmpAmount,_tmpUnit,_tmpExpiryDate,_tmpCreatedTimestamp);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
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
  public PantryItem fetchVaultEntryByName(final String name) {
    final String _sql = "SELECT * FROM pantry_items WHERE LOWER(TRIM(name)) = LOWER(TRIM(?)) LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
      final int _cursorIndexOfExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expiryDate");
      final int _cursorIndexOfCreatedTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "createdTimestamp");
      final PantryItem _result;
      if (_cursor.moveToFirst()) {
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        final String _tmpUnit;
        if (_cursor.isNull(_cursorIndexOfUnit)) {
          _tmpUnit = null;
        } else {
          _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
        }
        final String _tmpExpiryDate;
        if (_cursor.isNull(_cursorIndexOfExpiryDate)) {
          _tmpExpiryDate = null;
        } else {
          _tmpExpiryDate = _cursor.getString(_cursorIndexOfExpiryDate);
        }
        final long _tmpCreatedTimestamp;
        _tmpCreatedTimestamp = _cursor.getLong(_cursorIndexOfCreatedTimestamp);
        _result = new PantryItem(_tmpName,_tmpAmount,_tmpUnit,_tmpExpiryDate,_tmpCreatedTimestamp);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
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
  public int countVaultEntries() {
    final String _sql = "SELECT COUNT(*) FROM pantry_items";
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
