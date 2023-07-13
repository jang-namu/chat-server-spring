# API 명세서

---

---

### User_tb

---

| Index | Method | URI | Description | Req. Param.                                                                                   | Res. Param. | HTTP Status |
| --- | --- | --- | --- |-----------------------------------------------------------------------------------------------| --- | --- |
| 1 | GET | /users | 유저 정보 전체 조회 |                                                                                               |  |  |
| 2 | GET | /users/{uid} | 유저식별자로 특정 유저정보 조회 | uid: 유저아이디<br>(Long, required)                                                                |  |  |
| 3 | POST | /users | 유저 계정 등록 | name: 유저 이름<br>(String, required)<br>email: 유저 이메일<br>(String, required)                      |  |  |
| 4 | DELETE | /users | 유저 정보 삭제 | uid: 유저 아이디                                                              <br>(Long, required) |  |  |

### ChatRoom_tb

---

| Index | Method | URI | Description | Req. Param.                                             | Res. Param. | HTTP Status |
| --- | --- | --- | --- |---------------------------------------------------------| --- | --- |
| 1 | GET | /chatrooms | 채팅방 정보 전체 조회 |                                                         |  |  |
| 2 | GET | /chatrooms/{roomId} | 특정 채팅방 정보 조회 | roomId: 채팅방아이디<br>(Long, required)                      |  |  |
| 3 | POST | /chatrooms/{uid} | 채팅방 개설 | uid: 유저아이디                         <br>(Long, required) |  |  |
| 4 | DELETE | /chatrooms/{uid}/{roomId} | 채팅방 삭제 | uid: 유저아이디<br>(Long, required)<br>roomId: 채팅방아이디<br>(Long, required) |  |  |

### Chat_tb

---

| Index | Method | URI | Description | Req. Param.                                                      | Res. Param. | HTTP Status |
| --- | --- | --- | --- |------------------------------------------------------------------| --- | --- |
| 1 | GET | /chats/{uid} | 특정 유저의 모든 채팅정보 조회 | uid: 유저아이디<br>(Long, required)                                   |  |  |
| 2 | GET | /chats/{roomId} | 특정 채팅방의 모든 채팅 정보 조회 | roomId: 채팅방아이디<br>(Long, required)                               |  |  |
| 3 | GET | /chats/{uid}/{roomId} | 특정 유저, 특정 채팅방에 올라온 채팅 조회 | uid: 유저아이디<br>(Long, required)<br>roomId: 채팅방아이디<br>(Long, required) |  |  |
| 4 | POST | /chats | 채팅 정보 입력 | uid: 유저아이디<br>(Long, required)<br>roomId: 채팅방아이디<br>(Long, required)         |  |  |
| 5 | DELETE | /chats | 채팅 정보 삭제 | chatId: 채팅 아이디<br>(Long, required)                                   |  |  |

### Group_tb

---

| Index | Method | URI | Description | Req. Param. | Res. Param. | HTTP Status |
| --- | --- | --- | --- | --- | --- | --- |
| 1 | GET | /groups/{uid} | 유저 식별자로 포함된 그룹 조회 | uid: 유저아이디<br>(Long, required) |  |  |
| 2 | GET | /groups/{roomId} | 채팅방 식별자로 포함된 그룹조회 | roomId: 채팅방아이디<br>(Long, required) |  |  |
| 3 | GET | /groups/{uid}/{roomId} | 특정 유저, 특정 채팅방 그룹 조회 | uid: 유저아이디<br>(Long, required)<br>roomId: 채팅방아이디<br>(Long, required) |  |  |

### Permission_tb

---

| Index | Method | URI | Description | Req. Param. | Res. Param. | HTTP Status |
| --- | --- | --- | --- | --- | --- | --- |
| 1 | GET | /permissions/{uid}/{roomId} | 채팅방에 대한 유저의 권한 정보 조회 | uid: 유저아이디<br>(Long, required)<br>roomId: 채팅방아이디<br>(Long, required) |  |  |
| 2 | POST | /permissions | 유저에게 채팅방 권한 부여 | uid: 유저아이디<br>(Long, required)<br>roomId: 채팅방아이디<br>(Long, required)<br>status: 권한범위<br>(TINYINT, Default: 0(게스트)) |  |  |
| 3 | PUT | /permissions | 유저의 채팅방 권한 수정 | uid: 유저아이디<br>(Long, required)<br>roomId: 채팅방아이디<br>(Long, required)<br>status: 권한범위<br>(TINYINT, Default: 0(게스트)) |  |  |
| 4 | DELETE | /permissions | 유저의 채팅방 권한 삭제 | uid: 유저아이디<br>(Long, required)<br>roomId: 채팅방아이디<br>(Long, required) |  |  |