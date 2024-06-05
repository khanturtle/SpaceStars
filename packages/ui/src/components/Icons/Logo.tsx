export interface Props {
  width?: string
  height?: string
  viewBox?: string
}

const Logo = ({
  width = '41',
  height = '35',
  viewBox = '0 0 41 35',
  ...props
}: Props) => {
  return (
    <svg
      width={width}
      height={height}
      viewBox={viewBox}
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      {...props}
    >
      <g clip-path="url(#clip0_1013_4934)">
        <path
          d="M21.7252 1.1201L10.0663 12.9446L6.3115 16.7526C5.616 17.458 5.616 18.6122 6.3115 19.3175C7.007 20.0229 8.14504 20.0229 8.84054 19.3175L12.5954 15.51L14.7295 13.3451C15.425 12.6397 16.5636 12.6397 17.2591 13.3451C17.9546 14.051 17.9546 15.2052 17.2591 15.9105L15.125 18.0749L13.8455 19.3724C13.1495 20.0783 13.1495 21.2325 13.8455 21.9379C14.541 22.6432 15.6791 22.6432 16.3746 21.9379L17.654 20.6403L21.9949 16.2379C21.9998 16.2286 22.0052 16.2187 22.0106 16.2088C21.1759 15.6595 19.9604 15.1024 19.5325 14.9645C20.8076 14.2883 22.1661 14.0367 23.368 14.2411C23.692 13.8159 24.0484 13.4039 24.4373 13.0095C25.331 12.103 26.3163 11.3702 27.3385 10.8187C28.6368 10.1183 29.9948 9.70902 31.3013 9.60629C31.1989 10.9406 30.7905 12.3283 30.0907 13.6538C29.5485 14.68 28.8313 15.6694 27.9457 16.5675C27.5567 16.9619 27.1505 17.3234 26.7307 17.6514C26.9327 18.8709 26.6847 20.2486 26.0179 21.5418C25.8819 21.1073 25.3327 19.8751 24.791 19.0286L20.4279 23.4535L20.0752 23.8111C19.3797 24.5165 19.3797 25.6706 20.0752 26.376C20.7707 27.0819 21.9088 27.0819 22.6043 26.376L22.9569 26.0184L26.0184 22.914C26.7139 22.2087 27.852 22.2087 28.5475 22.914C29.243 23.6194 29.243 24.7741 28.5475 25.4795L25.4865 28.5838L22.6585 31.4519C21.963 32.1572 21.963 33.3114 22.6585 34.0167C23.354 34.7221 24.4925 34.7221 25.188 34.0167L39.221 19.7855C39.2643 19.7411 39.305 19.6955 39.3434 19.6477L40.7729 18.198L36.9812 3.84592L22.8296 0L21.7252 1.1201ZM2.69533 20.42L0.521625 22.6245C-0.173875 23.3299 -0.173875 24.4846 0.521625 25.1894C1.21712 25.8953 2.35517 25.8953 3.05067 25.1894L5.22492 22.9849C5.92042 22.2795 5.92042 21.1254 5.22492 20.42C4.87662 20.0674 4.41837 19.8905 3.96012 19.8905C3.50133 19.8905 3.04308 20.0674 2.69533 20.42ZM10.2294 23.0398C9.53062 23.7485 9.53062 24.8966 10.2294 25.6052C10.9276 26.3139 12.0602 26.3139 12.7584 25.6052C13.4572 24.8966 13.4572 23.7485 12.7584 23.0398C12.4096 22.6861 11.9513 22.5086 11.4942 22.5086C11.0359 22.5086 10.5782 22.6861 10.2294 23.0398Z"
          fill="url(#paint0_linear_1013_4934)"
        />
        <path
          d="M20.7168 5.20609L20.843 5.63018L21.2612 5.75872L20.843 5.88672L20.7168 6.31081L20.59 5.88672L20.1719 5.75872L20.59 5.63018L20.7168 5.20609Z"
          fill="white"
        />
        <path
          d="M26.3155 7.50013L26.5002 8.11759L27.1085 8.30491L26.5002 8.49169L26.3155 9.10914L26.1308 8.49169L25.5225 8.30491L26.1308 8.11759L26.3155 7.50013Z"
          fill="white"
        />
        <path
          d="M22.0025 10.1721L22.2008 10.8357L22.8557 11.0373L22.2008 11.2384L22.0025 11.902L21.8037 11.2384L21.1494 11.0373L21.8037 10.8357L22.0025 10.1721Z"
          fill="white"
        />
        <path
          d="M25.2622 2.26437L25.4892 3.02411L26.2378 3.25428L25.4892 3.48446L25.2622 4.24364L25.0347 3.48446L24.2861 3.25428L25.0347 3.02411L25.2622 2.26437Z"
          fill="white"
        />
        <path
          d="M29.7432 4.75893L29.8699 5.18247L30.2875 5.31101L29.8699 5.43956L29.7432 5.8631L29.6164 5.43956L29.1982 5.31101L29.6164 5.18247L29.7432 4.75893Z"
          fill="white"
        />
        <path
          d="M35.0337 5.00338L35.2607 5.76257L36.0092 5.99274L35.2607 6.22291L35.0337 6.98265L34.8067 6.22291L34.0576 5.99274L34.8067 5.76257L35.0337 5.00338Z"
          fill="white"
        />
        <path
          d="M32.888 10.1155L33.0765 10.7467L33.6989 10.9379L33.0765 11.1296L32.888 11.7608L32.6995 11.1296L32.0771 10.9379L32.6995 10.7467L32.888 10.1155Z"
          fill="white"
        />
        <path
          d="M36.3222 9.6832L36.5492 10.4424L37.2983 10.6731L36.5492 10.9033L36.3222 11.6625L36.0953 10.9033L35.3467 10.6731L36.0953 10.4424L36.3222 9.6832Z"
          fill="white"
        />
        <path
          d="M32.6744 15.4441L32.8591 16.0616L33.4679 16.2489L32.8591 16.4357L32.6744 17.0537L32.4902 16.4357L31.8809 16.2489L32.4902 16.0616L32.6744 15.4441Z"
          fill="white"
        />
        <path
          d="M36.3379 13.9065L36.4646 14.3301L36.8823 14.4586L36.4646 14.5872L36.3379 15.0112L36.2111 14.5872L35.793 14.4586L36.2111 14.3301L36.3379 13.9065Z"
          fill="white"
        />
        <path
          d="M35.2603 18.6182L35.4872 19.3774L36.2358 19.6076L35.4872 19.8383L35.2603 20.5975L35.0333 19.8383L34.2842 19.6076L35.0333 19.3774L35.2603 18.6182Z"
          fill="white"
        />
        <path
          d="M31.2954 19.3516L31.5224 20.1113L32.271 20.3415L31.5224 20.5717L31.2954 21.3308L31.0685 20.5717L30.3193 20.3415L31.0685 20.1113L31.2954 19.3516Z"
          fill="white"
        />
        <path
          d="M25.6309 14.3257C25.6309 14.8113 26.0192 15.2052 26.4986 15.2052C26.978 15.2052 27.3658 14.8113 27.3658 14.3257C27.3658 13.8395 26.9774 13.4456 26.4986 13.4456C26.0192 13.4456 25.6309 13.8395 25.6309 14.3257Z"
          fill="url(#paint1_linear_1013_4934)"
        />
      </g>
      <defs>
        <linearGradient
          id="paint0_linear_1013_4934"
          x1="20.3864"
          y1="0"
          x2="20.3864"
          y2="34.5457"
          gradientUnits="userSpaceOnUse"
        >
          <stop stop-color="#9027E2" />
          <stop offset="1" stop-color="#FF87CF" />
        </linearGradient>
        <linearGradient
          id="paint1_linear_1013_4934"
          x1="26.4983"
          y1="13.4456"
          x2="26.4983"
          y2="15.2052"
          gradientUnits="userSpaceOnUse"
        >
          <stop stop-color="#9027E2" />
          <stop offset="1" stop-color="#FF87CF" />
        </linearGradient>
        <clipPath id="clip0_1013_4934">
          <rect width="40.7729" height="34.5457" fill="white" />
        </clipPath>
      </defs>
    </svg>
  )
}

export default Logo
