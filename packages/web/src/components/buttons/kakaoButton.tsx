'use client'

import styles from './button.module.css'

export default function kakaoButton() {
  const handleClick = async () => {
    // await signIn('kakao')
    console.log('카카오 로그인')
  }

  return (
    <button
      className={`${styles.mdButton} ${styles.kakaoButton}`}
      type="button"
      onClick={handleClick}
      aria-label="카카오 로그인"
    >
      <div className="w-full flex justify-center items-center gap-2">
        <i className="w-[18px] h-[18px]">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="18"
            height="19"
            viewBox="0 0 18 19"
            fill="none"
          >
            <g clipPath="url(#clip0_667_1526)">
              <path
                fillRule="evenodd"
                clipRule="evenodd"
                d="M9.00002 1.1001C4.02917 1.1001 0 4.21306 0 8.05238C0 10.4401 1.5584 12.5451 3.93152 13.7971L2.93303 17.4446C2.84481 17.7669 3.21341 18.0238 3.49646 17.837L7.87334 14.9483C8.2427 14.9839 8.61808 15.0047 9.00002 15.0047C13.9705 15.0047 17.9999 11.8919 17.9999 8.05238C17.9999 4.21306 13.9705 1.1001 9.00002 1.1001Z"
                fill="black"
              />
            </g>
            <defs>
              <clipPath id="clip0_667_1526">
                <rect
                  width="17.9999"
                  height="18"
                  fill="white"
                  transform="translate(0 0.5)"
                />
              </clipPath>
            </defs>
          </svg>
        </i>
        <p>카카오 로그인</p>
      </div>
    </button>
  )
}
