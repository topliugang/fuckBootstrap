#-*-coding:utf-8-*-
import re

str = '分管副总、关联部门[3, 10-19]'

#/'fuck you' --> 'fuckyou'
def trim(str):
	return ''.join(str.split(' '))


# '1-4' --> [1, 2, 3, 4]
def getNumbers(str):
	#print 'geting number %s'%str
	start, end = str.split('-')
	return range(int(start), int(end)+1)



def parse(str):
	patten = re.compile(r'\[[,\d-]*\]')
	numbersets = patten.findall(trim(str))
	ret = []
	for numberset in numbersets:
		#print '-------------------begin------------------------------'
		#print 'numberset:',numberset
		numbers = numberset[1:-1].split(',')
		#print 'numbers:',numbers
		for num in numbers:
			#print 'num:',num
			if num.isdigit():
				ret.append(int(num))
				#print 'single number added :  %s' % num

			elif num.find('-') != -1:
				ret.extend(getNumbers(num))


		#print '-------------------end------------------------------'
		#print
	return ret

#print parse(str)

