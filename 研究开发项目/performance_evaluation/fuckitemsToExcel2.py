# -*- coding: utf-8 -*-
#coding=utf-8
import xlrd
import xlwt
import os
import parseNumbers

inxlsfile = 'fuckitems.xls'
outxlsfile = 'excel2.xls'
departmentdict = {u'办公室':'9',\
                    u'传输部':'15',\
                    u'工程部':'13',\
                    u'基建办':'19',\
                    u'计财部':'11',\
                    u'技术支持部':'14',\
                    u'客服部':'16',\
                    u'人力资源部':'10',\
                    u'集团用户部':'18',\
                    u'运营部':'12',\
                    u'监督部':'17',\
                    u'高新区综合科':'65',\
                    u'高新区工程科':'66',\
                    u'山亭区综合科':'51',\
                    u'山亭区运营科':'52',\
                    u'山亭区技术科':'53',\
                    u'山亭区工程科':'54',\
                    u'市中区综合科':'68',\
                    u'市中区运营科':'69',\
                    u'市中区营业厅':'72',\
                    u'市中区技术科':'70',\
                    u'市中区工程科':'71',\
                    u'薛城区综合科':'78',\
                    u'薛城区运营科':'79',\
                    u'薛城区营业厅':'82',\
                    u'薛城区技术科':'80',\
                    u'薛城区工程科':'81',\
                    u'峄城区综合科':'90',\
                    u'峄城区运营科':'91',\
                    u'峄城区技术科':'92',\
                    u'峄城区工程科':'93',\
                    u'台儿庄综合科':'101',\
                    u'台儿庄运营科':'104',\
                    u'台儿庄技术科':'102',\
                    u'台儿庄工程科':'103',\
                    u'滕州分公司办公室':'110',\
                    u'滕州分公司运营部':'111',\
                    u'滕州分公司财务部':'112',\
                    u'滕州分公司技术部':'113',\
                    u'滕州分公司器材部':'114',\
                    u'滕州分公司收费部':'115',\
                    u'滕州分公司工程部':'116',\
                    u'山亭营业部':'20',\
                    u'高新营业部':'21',\
                    u'市中营业部':'22',\
                    u'薛城营业部':'23',\
                    u'峄城营业部':'24',\
                    u'台儿庄营业部':'25',\
                    u'滕州分公司':'26'}



######################################################################################################
class Depitem:
    
    def __init__(self, id, name, description, value, reference, calculation,\
                 departmentid, category, date, inuse):
        self.id = id
        self.name = name
        self.description = description
        self.value = value
        self.reference = reference
        self.calculation = calculation
        self.departmentid = departmentid
        self.category = category
        self.date = date
        self.inuse = inuse

    def show(self):
        print self.id,
        print self.name,
        print self.description,
        print self.value,
        print self.reference,
        print self.calculation,
        print self.departmentid,
        print self.category,
        print self.date,
        print self.inuse,
        print self.id
        

    def writetosheet(self, worksheet, row):

        font = xlwt.Font()
        font.name = u'宋体'
        font.height = 240
        alignment = xlwt.Alignment()
        alignment.horz = xlwt.Alignment.HORZ_CENTER
        alignment.vert = xlwt.Alignment.VERT_CENTER
        style = xlwt.XFStyle()
        style.font = font
        style.alignment = alignment
        

       
        worksheet.write(row, 0, self.id, style)
        worksheet.write(row, 1, self.name, style)
        worksheet.write(row, 2, self.description, style)
        worksheet.write(row, 3, self.value, style)
        worksheet.write(row, 4, self.reference, style)
        worksheet.write(row, 5, self.calculation, style)
        worksheet.write(row, 6, self.departmentid, style)
        worksheet.write(row, 7, self.category, style)
        worksheet.write(row, 8, self.date, style)
        worksheet.write(row, 9, self.inuse, style)

class DepRelation:
    def __init__(self, id, itemid, depid):
        self.id = id
        self.itemid = itemid
        self.depid = depid


    def show(self):
        print self.id, self.itemid, self.depid

    def writetosheet(self, worksheet, row):
        font = xlwt.Font()
        font.name = u'宋体'
        font.height = 240
        alignment = xlwt.Alignment()
        alignment.horz = xlwt.Alignment.HORZ_CENTER
        alignment.vert = xlwt.Alignment.VERT_CENTER
        style = xlwt.XFStyle()
        style.font = font
        style.alignment = alignment
       
        worksheet.write(row, 0, self.id, style)
        worksheet.write(row, 1, self.itemid, style)
        worksheet.write(row, 2, self.depid, style)           

######################################################################################################       



if __name__ == '__main__':
    inbook = xlrd.open_workbook(inxlsfile)
    insheets = inbook.sheets()
    outbook = xlwt.Workbook(encoding = 'utf-8')
    outitemsheet = outbook.add_sheet('department_score_item', cell_overwrite_ok=True)
    outrelationsheet = outbook.add_sheet('department_score_relation', cell_overwrite_ok=True)
    itemtitle = Depitem('score_item_id', 'score_item_name', 'score_description', 'score_value', 'score_reference', \
                    'score_calculation_method', 'department_id', 'score_category_id', 'score_creation_date', 'score_in_use')
    itemtitle.writetosheet(outitemsheet, 0)
    relationtitle = DepRelation('department_score_relation_id', 'score_item_id', 'department_id')
    relationtitle.writetosheet(outrelationsheet, 0)
    itemcount = 1
    relationcount = 1
    for sheet in insheets:

        if sheet.name == 'notice':
            continue
        departmentname = sheet.name
        departmentid = int(departmentdict[departmentname])
        print u'读取部门: %s, 部门编号: %d'%(departmentname, departmentid)
        for m in range(1, sheet.nrows):
            item = Depitem(itemcount, sheet.cell_value(m, 1), sheet.cell_value(m, 2), sheet.cell_value(m, 3), sheet.cell_value(m, 4), \
                sheet.cell_value(m, 5), departmentid, sheet.cell_value(m, 0), '2014-4-14', 1)
            item.writetosheet(outitemsheet, itemcount)
            itemcount = itemcount+1
            numbers = parseNumbers.parse(sheet.cell_value(m, 6))
            if numbers:
                for number in numbers:
                    deprelation = DepRelation(relationcount, item.id, number) 
                    deprelation.writetosheet(outrelationsheet, relationcount)
                    relationcount = relationcount+1  

    if os.path.exists(outxlsfile):
        os.remove(outxlsfile)
    outbook.save(outxlsfile)
