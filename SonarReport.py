#!/usr/bin/python
# -*- coding: iso-8859-1 -*-
# -*- coding: utf-8 -*-



import os
import time
import platform
import zipfile
import urllib.request
from subprocess import call



if(platform.system()=="Windows"):
    path= os.environ['HOMEDRIVE'] + os.environ['HOMEPATH']+"/cloudbook/"
    if not os.path.exists(path):
        os.makedirs(path)
else:
    path = os.environ['HOME'] + os.sep + "cloudbook"
    if not os.path.exists(path):
        os.makedirs(path)

path = path+os.sep
# print (os.environ['HOMEDRIVE'])
# print(os.environ['HOMEPATH'])


print("We are going to start analyzing the specified project.")
print("It is important to have created a cloudbook folder, located in your computer's username folder")
print("in which all the projects that could be analyzed are located.")
print("It is highly recommended to download it unless you have already analized a project before")
print("IMPORTANT: MAKE SURE JAVA 11 DOWNLOADED. OTHERWISE, IT WON'T WORK")
print("Please, make sure it exists.")
print("¿Do you have SonarQube downloaded?")

while True:

	
	answer1 = input()
	answer1Low = answer1.lower()


	if answer1Low == "no":	
		
		print ("Downloading the latest version of SonaQube")

		time.sleep(3)


		url = 'https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-7.9.2.zip'


#		wget.download(url, path+'/sonarqube-8.1.0.31237.zip')


		file = path+'/sonarqube-7.9.2.zip'
		r= urllib.request.urlopen(url)
		f = open(file, "wb") 
		f.write(r.read()) 
		f.close()

		with zipfile.ZipFile(path+"/sonarqube-7.9.2.zip", 'r') as zip_ref:
			zip_ref.extractall(path)


		print("SonarQube downloaded in the cloudbook projects folder")


		break	

	
	elif answer1Low == 'yes':


		print ("SonarQube downloaded. Please, make sure it is downloaded in the cloudbook projects folder")
		
		
		break
	else:
		print("Please, answer yes or no")



print("¿Do you have the SonarQube Scanner downloaded?")



if(platform.system()=="Windows"):

	while True:

		answer2 = input()
		answer2Low = answer2.lower()

		if answer2Low == 'no':
			
			print("Downloading the latest version of SonarQube Scanner")

			print(platform.system())

			url = 'https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-4.2.0.1873-windows.zip'

			file = path+'/sonar-scanner-cli-4.2.0.1873-windows.zip'
			r= urllib.request.urlopen(url)
			f = open(file, "wb") 
			f.write(r.read()) 
			f.close()


			with zipfile.ZipFile(path+"sonar-scanner-cli-4.2.0.1873-windows.zip", 'r') as zip_ref:
				zip_ref.extractall(path)
		
			print("SonarQube Scanner downloaded in the cloudbook projects folder")

			time.sleep(2)

			break 


		elif answer2Low == 'yes':

			print("SonarQube Scanner downloaded. Please, make sure it is downloaded in the cloudbook projects folder")

			print("SonarQube and SonarQube Scanner downloaded correctly")

			time.sleep(2)

			break

		else:
			
			print("Please, answer yes or no")


############################################################# PARA LINUX ############################################			
else: 
	while True:

		answer2 = input()
		answer2Low = answer2.lower()



		if answer2Low == 'no':
			
			print("Downloading the latest version of SonarQube Scanner")

			#if(platform.system()=="Windows"):
				#print ("Su sistema operativo es ")
			print(platform.system())

			url = 'https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-4.2.0.1873-linux.zip'

#			wget.download(url, path+'/sonar-scanner-cli-4.2.0.1873-linux.zip')
			file = path+'/sonar-scanner-cli-4.2.0.1873-linux.zip'
			r= urllib.request.urlopen(url)
			f = open(file, "wb") 
			f.write(r.read()) 
			f.close()


			with zipfile.ZipFile(path+"sonar-scanner-cli-4.2.0.1873-linux.zip", 'r') as zip_ref:
				zip_ref.extractall(path)
			
		#	print("Opening SonarQube")

			
			print("SonarQube Scanner downloaded in the cloudbook projects folder")

			time.sleep(2)

			break 


		elif answer2Low == 'yes':

			print("SonarQube Scanner downloaded. Please, make sure it is downloaded in the cloudbook projects folder")

			print("SonarQube and SonarQube Scanner downloaded correctly")

			time.sleep(2)

			break
		###################################################################################
		#Para borrar el zip descargado
			# time.sleep(3)
			# shutil.rmtree("sonar-scanner-cli-4.2.0.1873-windows.zip")
		################################################################################
		else:
			
			print("Please, answer yes or no")

#####################################################################################################################



# plugin = "https://github.com/lequal/sonar-cnes-report/releases/download/3.1.0/sonar-cnes-report-3.1.0.jar"	

# wget.download(plugin, path+'/sonarqube-7.9.2/extensions/plugins/sonar-cnes-report-3.1.0.jar')




os.chdir(path+"sonarqube-7.9.2/conf")

file = open('sonar.properties', 'w+')
file.writelines(["sonar.search.port=0"])
file.close()



os.chdir(path+"sonarqube-7.9.2/extensions/plugins")
if(os.path.exists("sonar-security-report.jar")):
	call("rm sonar-security-report.jar", shell=True)
call("cp C:/Users/gonza/Desktop/sonar-security-report.jar .", shell=True)



os.chdir(path+"/sonarqube-7.9.2/bin/windows-x86-64")

call("start StartSonar.bat", shell=True)

time.sleep(35)

print("SonarQube activated")
print("Enter the project's name you want to analyze, including capital letters")

if(platform.system()=="Windows"):

	while True:

		answer3 = input()
		exist1 = os.path.exists(path+answer3)

		path2 = path+answer3
		username = os.path.join(os.getlogin())

		if exist1:
			#Configuring projet's du_files
			os.chdir(path+"/sonar-scanner-4.2.0.1873-windows/conf")

			call("rm *", shell=True)

			file = open('sonar-scanner.properties', 'w+')
			file.writelines(["sonar.projectKey="+answer3+"-du_files",
			"\nsonar.projectName="+answer3+"-du_files",
			"\nsonar.projectVersion=1.0" ,
			"\nsonar.projectBaseDir=C:/Users/"+username+"/cloudbook/"+answer3+"/distributed",
			"\nsonar.sources=du_files" ])
			file.close()


			os.chdir(path+"/sonar-scanner-4.2.0.1873-windows/bin")
			call("start sonar-scanner.bat", shell=True)
			

			time.sleep(20)


			#Configuring project's original file
			os.chdir(path+"/sonar-scanner-4.2.0.1873-windows/conf")
			file = open('sonar-scanner.properties', 'w+')
			file.writelines(["sonar.projectKey="+answer3+"-original",
			"\nsonar.projectName="+answer3+"-original",
			"\nsonar.projectVersion=1.0" ,
			"\nsonar.projectBaseDir=C:/Users/"+username+"/cloudbook/"+answer3,
			"\nsonar.sources=original" ])
			file.close()


			os.chdir(path+"/sonar-scanner-4.2.0.1873-windows/bin")
			call("start sonar-scanner.bat", shell=True)
			

			time.sleep(20)


			break

			
		else: 
		
			print("That project doesnt exist. Please, try another project name")
			

	#while True:
	time.sleep(5)


else:
################################################LINUX##################################
	while True:

		answer3 = input()
		exist1 = os.path.exists(path+answer3)


		path2 = path+answer3
		username = os.path.join(os.getlogin())

#		print (username)


		if exist1:
		
			os.chdir(path+"/sonar-scanner-4.2.0.1873-windows/conf")

			call("rm *", shell=True)

			file = open('sonar-scanner.properties', 'w+')
			file.writelines(["sonar.projectKey="+answer3+"-du_files",
			"\nsonar.projectName="+answer3+"-du_files",
			"\nsonar.projectVersion=1.0" ,
			"\nsonar.projectBaseDir=C:/Users/"+path+answer3+"/distributed",
#			"\nsonar.projectBaseDir=C:/Users/gonza/cloudbook/"+answer3+"/distributed",
			"\nsonar.sources=du_files" ])
			file.close()

	#os.chdir(path+answer3)

	#	call("cp ./original/* ./distributed/du_files")

			os.chdir(path+"/sonar-scanner-4.2.0.1873-windows/bin")
			call("start sonar-scanner.bat", shell=True)
			

			time.sleep(20)



			os.chdir(path+"/sonar-scanner-4.2.0.1873-windows/conf")
			file = open('sonar-scanner.properties', 'w+')
			file.writelines(["sonar.projectKey="+answer3+"-original",
			"\nsonar.projectName="+answer3+"-original",
			"\nsonar.projectVersion=1.0" ,
			"\nsonar.projectBaseDir=C:/Users/"+username+"/cloudbook/"+answer3,
#			"\nsonar.projectBaseDir=C:/Users/gonza/cloudbook/"+answer3,
			"\nsonar.sources=original" ])
			file.close()


			os.chdir(path+"/sonar-scanner-4.2.0.1873-windows/bin")
			call("start sonar-scanner.bat", shell=True)
			

			time.sleep(20)


			break

			
		else: 
		
			print("That project doesnt exist. Please, try another project name")
			


print('Project '+ answer3+ ' analized')

print('Do you want to generate a full security report of your project?')

while True:
	answerReport = input()
	answerReportLower = answerReport.lower()


	if answerReportLower == "no":	
		print('Go to "http://localhost:9000" to consult the results. You can manually generate de report by the Web UI')
		

		time.sleep(3)
		break

	elif answerReportLower == "yes":

		os.chdir(path)
		call("mkdir Reports", shell=True)
		os.chdir(path+"Reports")
		call("mkdir "+answer3, shell=True)
		os.chdir(path+"Reports/"+answer3)
		call("mkdir du_files", shell=True)
		call("mkdir original", shell=True)

		os.chdir(path+"Reports/"+answer3+"/original")
		call("rm *", shell=True)
		call("java -jar C:/Users/gonza/Desktop/sonar-security-report.jar -p "+answer3+"-original", shell=True)

		time.sleep (5)
		os.chdir(path+"Reports/"+answer3+"/du_files")
		call("rm *", shell=True)
		call("java -jar C:/Users/gonza/Desktop/sonar-security-report.jar -p "+answer3+"-du_files", shell=True)




		print("Please go to cloudbook/Reports and verify the project analyzed. ")
		print("You will see both original and du_files report generated in 2 specified folders.")

		break

	else:
		print('Please, answer yes or no')



time.sleep(5)
