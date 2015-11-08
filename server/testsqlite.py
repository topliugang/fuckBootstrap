# -*- coding:utf-8 -*-  

import sqlite3

def insert():
	cx = sqlite3.connect("fuck.db")
	cu = cx.cursor() 
	cu.execute("insert into member(name) values('liugang')")  
	cx.commit()


def select():
	cx = sqlite3.connect("fuck.db")
	cu = cx.cursor() 
	cu.execute("select * from member")
	print cu.fetchall()




if __name__ == '__main__':
	select()