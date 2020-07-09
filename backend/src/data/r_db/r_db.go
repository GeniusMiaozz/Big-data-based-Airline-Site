package r_db

import (
	"database/sql"
	"fmt"

	"Airline/config"
)

type r_db_operation interface {
	//establish connection with database
	connect() error
	//close connection with database
	disconnect() error
	//formal query `select [attributes] from [tables] where [condition]`
	Query(attributes []string, tables []string, condition string) (*sql.Rows, error)
	//formal insertion `insert into [table]([attributes]) values([values])`
	Insert(table string, attributes []string, values []string) error
	//formal update `update [table] set [attribute]=[value] where [condition]`
	Update(table string, attribute string, value string, condition string) error
	//formal delete `delete from [table] where [condition] [cascade|strict]`
	Delete(table string, condition string, cascadeOrStrict bool) error
	//special query
	QuerySQL(query string) (*sql.Rows, error)
	//special SQL
	ExecSQL(query string) error
	//begin a transaction
	BeginTransaction() (*sql.Tx, error)
	//Commit a transaction
	Commit(*sql.Tx) error
	//Rollback a transaction
	Rollback(*sql.Tx) error
	//standard query for transaction
	TxQuery(attributes []string, tables []string, condition string, tx *sql.Tx) (*sql.Rows, error)
	//standard insert for transaction
	TxInsert(table string, attributes []string, values []string, tx *sql.Tx) error
	//standard update for transaction
	TxUpdate(table string, attribute string, value string, condition string, tx *sql.Tx) error
	//standard delete for transaction
	TxDelete(table string, condition string, cascadeOrStrict bool, tx *sql.Tx) error
	//free format query for transaction
	TxQuerySQL(query string, tx *sql.Tx) (*sql.Rows, error)
	//free format exec for transcation
	TxExecSQL(query string, tx *sql.Tx) error
}

var (
	r_db      r_db_operation
	dBHandle  *sql.DB
	connected bool
)

func init() {
	connected = false
	var path = "../config/%s.toml"
	switch config.A_system_config.RDatabase {
	case "mysql":
		path = fmt.Sprintf(path, config.A_system_config.RDatabase)
		var a_mysql = new(mysql)
		config.LoadConfig(path, a_mysql)
		r_db = a_mysql
	}
}

func Connect() (r_db_operation, error) {
	if connected {
		return r_db, nil
	} else {
		err := r_db.connect()
		if err != nil {
			return nil, err
		}
		connected = true
		return r_db, nil
	}
}

func Disconnect() error {
	if connected {
		err := r_db.disconnect()
		if err != nil {
			return err
		}
		connected = false
		return nil
	}
	return nil
}
