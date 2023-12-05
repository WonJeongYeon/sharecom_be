package com.sharecom.sharecom_be.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
  /**
   * 200 : 요청 성공
   */
  SUCCESS("요청에 성공하였습니다.", HttpStatus.OK),
  FAIL("요청에 실패하였습니다.", HttpStatus.BAD_REQUEST),

  /**
   * 400 : Request, Response 오류
   */
  LOGIN_EMPTY_PHONENUMBER(
    "핸드폰 번호를 입력해주세요.",
    HttpStatus.BAD_REQUEST
  ),
  USERS_EMPTY_EMAIL("이메일을 입력해주세요.", HttpStatus.BAD_REQUEST),
  TEST_EMPTY_COMMENT("코멘트를 입력해주세요.", HttpStatus.BAD_REQUEST),
  POST_USERS_INVALID_EMAIL(
    "이메일 형식을 확인해주세요.",
    HttpStatus.BAD_REQUEST
  ),
  POST_USERS_EXISTS_EMAIL(
    "이미 사용 중인 이메일입니다.",
    HttpStatus.BAD_REQUEST
  ),
  POST_TEST_EXISTS_MEMO("중복된 메모입니다.", HttpStatus.BAD_REQUEST),
  UNKNOWN_PARTS_TYPE("잘못된 부품 종류입니다.", HttpStatus.BAD_REQUEST),
  WS_INVALID_TYPE(
    "잘못된 요청 타입입니다. (ONLY AVAILABLE SEND/DELETE)",
    HttpStatus.BAD_REQUEST
  ),

  RESPONSE_ERROR("데이터를 불러오는 데 실패하였습니다.", HttpStatus.NOT_FOUND),

  DUPLICATED_EMAIL("이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST),
  INVALID_MEMO("존재하지 않는 메모입니다.", HttpStatus.NOT_FOUND),
  FAILED_TO_LOGIN(
    "존재하지 않는 계정이거나 잘못된 비밀번호입니다.",
    HttpStatus.NOT_FOUND
  ),
  FAILED_TO_SAVE_RENTAL("대여정보 저장에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
  EMPTY_JWT("JWT를 입력해주세요.", HttpStatus.UNAUTHORIZED),
  INVALID_JWT("유효하지 않은 JWT입니다.", HttpStatus.UNAUTHORIZED),
  INVALID_REFRESH(
    "유효하지 않은 REFRESHTOKEN 입니다.",
    HttpStatus.UNAUTHORIZED
  ),
  VALIDITY_TIMEOUT_JWT("만료된 토큰입니다.", HttpStatus.UNAUTHORIZED),
  INVALID_USER_JWT("권한이 없는 유저의 접근입니다.", HttpStatus.FORBIDDEN),
  NOT_FIND_USER("일치하는 유저가 없습니다.", HttpStatus.NOT_FOUND),
  NOT_EXISTS_USER("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND),
  NOT_EXISTS_DESKTOP("존재하지 않는 PC입니다.", HttpStatus.NOT_FOUND),
  INVALID_OAUTH_TYPE(
    "알 수 없는 소셜 로그인 형식입니다.",
    HttpStatus.BAD_REQUEST
  ),
  NOT_EXISTS_CHAT_ROOM("존재하지 않는 채팅방입니다.", HttpStatus.NOT_FOUND),
  NOT_EXISTS_CHAT_LOG("잘못된 채팅 고유값입니다.", HttpStatus.NOT_FOUND),
  USED_DESKTOP("대여 중인 PC는 삭제할 수 없습니다.", HttpStatus.BAD_REQUEST),

  /**
   * 500 :  Database, Server 오류
   */
  DATABASE_ERROR(
    "데이터베이스 연결에 실패하였습니다.",
    HttpStatus.INTERNAL_SERVER_ERROR
  ),
  SERVER_ERROR(
    "서버와의 연결에 실패하였습니다.",
    HttpStatus.INTERNAL_SERVER_ERROR
  ),
  PASSWORD_ENCRYPTION_ERROR(
    "비밀번호 암호화에 실패하였습니다.",
    HttpStatus.INTERNAL_SERVER_ERROR
  ),
  PASSWORD_DECRYPTION_ERROR(
    "비밀번호 복호화에 실패하였습니다.",
    HttpStatus.INTERNAL_SERVER_ERROR
  ),

  MODIFY_FAIL_USERNAME("유저네임 수정 실패", HttpStatus.INTERNAL_SERVER_ERROR),
  DELETE_FAIL_USERNAME("유저 삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR),
  MODIFY_FAIL_MEMO("메모 수정 실패", HttpStatus.INTERNAL_SERVER_ERROR),

  UNEXPECTED_ERROR(
    "예상치 못한 에러가 발생했습니다.",
    HttpStatus.INTERNAL_SERVER_ERROR
  );

  private final HttpStatus httpStatus;
  private final String message;
  private final int code;

  private BaseResponseStatus(String message, HttpStatus httpStatus) {
    this.message = message;
    this.code = httpStatus.value();
    this.httpStatus = httpStatus;
  }
}
