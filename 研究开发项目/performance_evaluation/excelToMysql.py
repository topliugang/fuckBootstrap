# -*- coding: utf-8 -*-
import MySQLdb
import xlrd

def insert(tablename, colnames, values):
	print 'inserting ',tablename

	try:
		coon = MySQLdb.connect(host='10.7.0.124', user='root', passwd='1234', db='pfe', port=3306, charset='utf8')
		cur = coon.cursor()

		count = cur.execute('desc %s'%tablename)
		results = cur.fetchall()

		colnamestr = results[0][0]
		coltypestr = '%s'

		for i in range(1, count):
			colname = results[i][0]
			coltype = results[i][1]
			colnamestr = colnamestr+', '+colname
			coltypestr = coltypestr+', %s'

		sql = 'insert into '+tablename+'('+colnamestr+') '+'values('+coltypestr+')'
		cur.executemany(sql, values)
		coon.commit()
		coon.close()

	except MySQLdb.Error, e:
		print 'mysql error %d, %s'%(e.args[0], e.args[1])


def fuck(filename):
	book = xlrd.open_workbook(filename)
	sheets = book.sheets()
	for sheet in sheets:

		tablename = sheet.name
		colnames =[]
		for cell in sheet.row(0):
			if cell.ctype != 0:
				colnames.append(cell.value)
		colnum = len(colnames)
		values = []
		for n in range(1, sheet.nrows):
			realrow = []
			for m in range(0, colnum):
				realrow.append(sheet.cell(n, m).value)
			values.append(realrow)
		#for cell in colnames:
		#	print cell
		#for fuck in values:
		#	for sb in fuck:
		#		print sb,
		#	print
		insert(tablename, colnames, values )


if __name__ == '__main__':
	fuck('excel.xls')
	fuck('excel2.xls')
