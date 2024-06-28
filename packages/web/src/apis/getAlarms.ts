import { ApiResponseType, AlarmListType } from '@/types/type';

const ALARM_LIST_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/alarm/list`;
const NICKNAME_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/auth/nickname/search`;
const PROFILE_IMAGE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/profile/image/main`;

// 알림 리스트 조회
export async function getAlarms(
    token?: string,
): Promise<AlarmListType[] | undefined> { 

    try {
        const response = await fetch(ALARM_LIST_URL, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: token ? token : '',
            },
        });

        if (!response.ok) {
            console.error("Failed to fetch alarms, Response Status:", response.status); // 응답 실패 로그
            console.error("Error Response:", response); // 에러 응답 본문 로그
            throw new Error('Failed to get alarm list');
        }
        const data = await response.json();
        console.log("API Response Data: ", data);
        return data.result.alarmList;
    } catch (error) {
        console.error(error);
        return;
    }
}

// uuid로 닉네임 조회
export interface NicknameType {
    nickname: string;
}

export async function getNicknameByUuid(
    uuid: string
): Promise<NicknameType | null> {

    try {
        const response = await fetch(`${NICKNAME_URL}/${uuid}`);
        const data = await response.json();

        if (!data) {
            throw new Error('Failed to getNicknameByUuid');
        }
        return data.result;
    } catch (error) {
        console.error(error);
        return null;
    }
}

// uuid로 프로필 사진 조회
export interface ProfileImageType {
    profileImageUrl: string;
}

export async function getProfileImageByUuid(
    uuid: string,
    token?: string,
): Promise<ProfileImageType | null> {

    try {
        const response = await fetch(`${PROFILE_IMAGE_URL}/${uuid}`, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: token ? token : '',
            },
        });

        const data = await response.json();

        if (!data) {
            throw new Error('Failed to getProfileImageByUuid');
        }
        return data.result;
    } catch (error) {
        console.error(error);
        return null;
    }
}
