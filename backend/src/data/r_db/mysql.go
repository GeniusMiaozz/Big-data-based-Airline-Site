package r_db

import (
	"database/sql"
	"fmt"
	"strings"

	_ "github.com/go-sql-driver/mysql"
)

type mysql struct {
	Ip           string `toml:"ip"`
	Port         int64  `toml:"port"`
	Protocol     string `toml:"protocol"`
	User         string `toml:"user"`
	Password     string `toml:"password"`
	Database     string `toml:"database"`
	Charset      string `toml:"charset"`
	MaxConns     int    `toml:"maxconns"`
	MaxIdleConns int    `toml:"maxidleconns"`
}

func (db *mysql) connect() error {
	var uri = fmt.Sprintf("%s:%s@%s(%s:%d)/%s?charset=%s",
		db.User,
		db.Password,
		db.Protocol,
		db.Ip,
		db.Port,
		db.Database,
		db.Charset)
	var err error
	dBHandle, err = sql.Open("mysql", uri)
	if err != nil {
		return err
	}
	dBHandle.SetMaxOpenConns(db.MaxConns)
	dBHandle.SetMaxIdleConns(db.MaxIdleConns)
	if err = dBHandle.Ping(); nil != err {
		return err
	}
	return nil
}

func (db *mysql) disconnect() error {
	return dBHandle.Close()
}

func (db *mysql) Query(attributes []string, tables []string, condition string) (*sql.Rows, error) {
	query := `select ` + strings.Join(attributes, ",") + ` from ` + strings.Join(tables, ",")
	if condition != "" {
		query += ` where ` + condition
	}
	return db.QuerySQL(query)
}

func (db *mysql) Insert(table string, attributes []string, values []string) error {
	query := "insert into " + table
	if attributes != nil {
		query += "(" + strings.Join(attributes, ",") + ")"
	}
	query += " values(" + strings.Join(values, ",") + ")"
	return db.ExecSQL(query)
}

func (db *mysql) Update(table string, attribute string, value string, condition string) error {
	query := "update " + table + " set " + attribute + "=" + value
	if condition != "" {
		query += " where " + condition
	}
	return db.ExecSQL(query)
}

func (db *mysql) Delete(table string, condition string, cascadeOrStrict bool) error {
	query := "delete from " + table
	if condition != "" {
		query += " where " + condition
	}
	if cascadeOrStrict {
		query += " cascade"
	}
	return db.ExecSQL(query)
}

func (db *mysql) QuerySQL(query string) (*sql.Rows, error) {
	return dBHandle.Query(query)
}

func (db *mysql) ExecSQL(query string) error {
	var (
		stmt *sql.Stmt
		err  error
	)
	stmt, err = dBHandle.Prepare(query)
	if err != nil {
		return err
	}
	_, err = stmt.Exec()
	return err
}

func (db *mysql) BeginTransaction() (*sql.Tx, error) {
	return dBHandle.Begin()
}

func (db *mysql) Commit(tx *sql.Tx) error {
	return tx.Commit()
}

func (db *mysql) Rollback(tx *sql.Tx) error {
	return tx.Rollback()
}

func (db *mysql) TxQuery(attributes []string, tables []string, condition string, tx *sql.Tx) (*sql.Rows, error) {
	query := `select ` + strings.Join(attributes, ",") + ` from ` + strings.Join(tables, ",")
	if condition != "" {
		query += ` where ` + condition
	}
	return db.TxQuerySQL(query, tx)
}

func (db *mysql) TxInsert(table string, attributes []string, values []string, tx *sql.Tx) error {
	query := "insert into " + table
	if attributes != nil {
		query += "(" + strings.Join(attributes, ",") + ")"
	}
	query += " values(" + strings.Join(values, ",") + ")"
	return db.TxExecSQL(query, tx)
}

func (db *mysql) TxUpdate(table string, attribute string, value string, condition string, tx *sql.Tx) error {
	query := "update " + table + " set " + attribute + "=" + value
	if condition != "" {
		query += " where " + condition
	}
	return db.TxExecSQL(query, tx)
}

func (db *mysql) TxDelete(table string, condition string, cascadeOrStrict bool, tx *sql.Tx) error {
	query := "delete from " + table
	if condition != "" {
		query += " where " + condition
	}
	if cascadeOrStrict {
		query += " cascade"
	}
	return db.TxExecSQL(query, tx)
}

func (db *mysql) TxQuerySQL(query string, tx *sql.Tx) (*sql.Rows, error) {
	return tx.Query(query)
}

func (db *mysql) TxExecSQL(query string, tx *sql.Tx) error {
	var (
		stmt *sql.Stmt
		err  error
	)
	stmt, err = tx.Prepare(query)
	if err != nil {
		return err
	}
	_, err = stmt.Exec()
	return err
}
