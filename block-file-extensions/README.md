# block-file-extensions
[마드라스 체크] - flow 서버 개발자 과제(파일 확장자 차단)

노션 링크 첨부 : https://efficient-squirrel-1f7.notion.site/a15a0044db7442b3a773a23e94da98d5

application.yml 파일에서
1차 실행
ddl-auto: create 
주석 처리 후
hbm2ddl.auto: validate 추가 후 재실행

[실행]
실행위치 : /Users/kimhuiji/Desktop/flow-project_/block-file-extensions/build/libs

scp -i flow-project-ec2-keypair.pem block-file-extensions-0.0.1-SNAPSHOT.jar ubuntu@3.37.100.7:~


