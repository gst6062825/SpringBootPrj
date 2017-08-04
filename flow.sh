#!/bin/sh

USER=$(whoami)
DATE=$(date +'%Y%m%d')
TIME=$(date +'%Y%m%d_%H%M')
BACKUP="/data/tomcat_flow/jar/backup"
UPDATE="/data/tomcat_flow/jar/update"
LOG="/data/tomcat_flow/jar/log"
LOGFILE=$LOG/$DATE
KILLNUM=1



##################################################################
#               Different Flow Use Different Config             #
#                         Config.                                       # 
##################################################################
WAR="flow.jar"
DEPLOY="/data/tomcat_flow"
BIN="/data/tomcat_flow"

#####################################################################
#-- dev --
# TEST For Flow ip & Port 182.254.136.227:18089
#####################################################################
usage()
{
	echo -e "##################### Usage ############################\n"
	echo -e "  $0  \n To Manage The Flow Server $WAR \n"	
	echo -e "e.g.  $0  start|stop|restart|update|rollback\n"
}


stop()
{
	PID=$(ps ux |grep "$WAR" | grep -v grep |awk '{print $2}')
				kill -9 $PID > /dev/null 2>&1
	
}


start()
{
        PID=$(ps ux |grep "$WAR" | grep -v grep |awk '{print $2}')
        if [[ $PID -gt 0 ]]
        then
		echo "Warning Service has started The $WAR  ..."
		exit 1
	else
		echo "Starting The $WAR 's Flow.jar ..."
		/data/tomcat_flow/start.sh &  > /dev/null 2>&1
	fi
	
}


restart()
{
	stop
	start
}


update()
{
        if [ ! -f $UPDATE/$WAR ]
        then
                echo "Please Upload The $WAR To $UPDATE"
                exit -1
        else
		SMD5=$(md5sum $UPDATE/$WAR  |awk '{print $1}')
	fi


	if [ x$MD5 == x$SMD5 ]
	then
		#Backup The War Packages
		echo "Backuping The Flow Jar Package."
		cp $DEPLOY/$WAR  $BACKUP/${WAR}_${TIME}

		#Stop The Servers
		if [ $(ps ux |grep "$WAR" | grep -v grep |wc -l) -eq 0 ]
		then
			echo "$WAR Is Not Exist, Process Is Not Run  Continue..."
			sleep 2
		else
			stop
		fi

		#Copping The New Packages
		echo "Copying The New $WAR to $DEPLOY"
		if [ -f $UPDATE/$WAR ]
		then
			rm -f $DEPLOY/$WAR
			cp -f $UPDATE/$WAR   $DEPLOY/
		else
			echo "Not Found The $WAR Package In $UPDATE"
			exit -13
		fi

		#Start The Servers
		start
	else
		echo "Your Md5 Value Is: $MD5 || But The SMD5 Value Is $SMD5"
	fi

	
}	


updat()
{
        if [ ! -f $UPDATE/$WAR ]
        then
                echo "Please Upload The $WAR To $UPDATE"
                exit -1
        else
                SMD5=$(md5sum $UPDATE/$WAR  |awk '{print $1}')
        fi


        #if [ x$MD5 == x$SMD5 ]
        if [ -f $UPDATE/$WAR ]
        then
                #Backup The War Packages
                echo "Backuping The Flow Jar Package."
                cp $DEPLOY/$WAR  $BACKUP/${WAR}_${TIME}

                #Stop The Servers
                if [ $(ps ux |grep "$WAR" | grep -v grep |wc -l) -eq 0 ]
                then
                        echo "$WAR Is Not Exist, Process Is Not Run  Continue..."
                        sleep 2
                else
                        stop
                fi

                #Copping The New Packages
                echo "Copying The New $WAR to $DEPLOY"
                if [ -f $UPDATE/$WAR ]
                then
                        rm -f $DEPLOY/$WAR
                        cp -f $UPDATE/$WAR   $DEPLOY/
                else
                        echo "Not Found The $WAR Package In $UPDATE"
                        exit -13
                fi

                #Start The Servers
                start
        else
                echo "Your Md5 Value Is: $MD5 || But The SMD5 Value Is $SMD5"
        fi


}

rollback()
{
        if [ x$BACK != x ]
        then
                if [ -f $BACKUP/${WAR}_${BACK} ]
                then
                	if [ $(ps ux |grep "$WAR" | grep -v grep |wc -l) -eq 0 ]
                	then
                        	echo "$WAR Is Not Exist, Process Is Not Run  Continue..."
                        	sleep 2
                	else
                        	stop
                	fi

                	#Copping The Rollback Packages
                	echo "Copying The Old ${WAR}_${BACK} to $DEPLOY"
                        rm -f $DEPLOY/$WAR
                        cp -f $BACKUP/${WAR}_${BACK}   $DEPLOY/$WAR

                	#Start The Servers
                	start
                else
                        echo "Not Found The ${WAR}_${BACK} Package In $BACKUP"
                        exit -13
                fi


        else
                echo "Sure The Time Is Exist! Your Input Is $BACK"
                exit -15
        fi
}




#################################################################################
#-- Choose $1 And Run Different Function --
#"update" | "stop" | "start" | "restart" | "usage" |"rollback"
#################################################################################
case $1 in
	"update")
		MD5=$2
		update
		;;
        "updat")
                updat
                ;;
	"rollback")
		BACK=$2
		rollback
		;;
	"stop")
		stop
		;;
	"start")
		start
		;;
	"restart")
		restart
		;;
	*)
		usage
		;;
esac


