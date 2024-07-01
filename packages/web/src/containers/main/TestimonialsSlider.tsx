'use client'

import { useEffect, useRef } from 'react'

export default function TestimonialsSlider() {
  const testimonials = [
    {
      quote:
        '우주별 덕분에 실력이 비슷한 듀오를 만났어요. 이제 랭크 올리는 게 훨씬 재미있어졌죠!',
      author: '김솔로, 리그 오브 레전드 플레이어',
    },
    {
      quote:
        'TFT에서 함께 전략을 공유할 친구를 찾기 어려웠는데, 우주별에서 완벽한 파트너를 만났어요.',
      author: '이전략, 롤토체스 매니아',
    },
    {
      quote:
        '우주별을 통해 만난 스쿼드원들과 함께 치킨 먹기가 훨씬 쉬워졌어요!',
      author: '박배그, 배틀그라운드 스쿼드',
    },
    {
      quote:
        '보스 레이드를 함께 할 길드원을 찾고 있었는데, 우주별에서 훌륭한 팀원들을 만났습니다.',
      author: '정메이플, 메이플스토리 길드마스터',
    },
    {
      quote:
        '우주별에서 만난 팀원들과 함께 발로란트 대회에 나가 우승까지 했어요! 정말 감사합니다.',
      author: '최에이스, 발로란트 프로 지망생',
    },
    {
      quote:
        '오버워치2에서 포지션별로 훌륭한 팀원을 구할 수 있어서 정말 좋아요. 이제 경쟁전이 기다려집니다!',
      author: '강탱커, 오버워치2 열혈 팬',
    },
    {
      quote:
        '로스트아크에서 레이드를 함께 돌 파티원을 구하기 어려웠는데, 우주별에서 완벽한 팀을 꾸렸어요.',
      author: '황아크, 로스트아크 헌터',
    },
  ]

  const sliderRef = useRef<HTMLDivElement>(null)
  const intervalRef = useRef<ReturnType<typeof setInterval> | null>(null)

  useEffect(() => {
    const slider = sliderRef.current
    if (!slider) return

    const items =
      slider.querySelectorAll<HTMLDivElement>('.testimonial-item') || []
    let currentIndex = 0

    function slide() {
      currentIndex = (currentIndex + 1) % (items?.length - 1 || 0)
      if (slider) {
        slider.style.transform = `translateX(-${currentIndex * 33.3}%)`
      }
    }

    // 컴포넌트가 마운트될 때 인터벌 시작
    intervalRef.current = setInterval(slide, 5000)

    // 컴포넌트가 언마운트될 때 인터벌 정리
    return () => {
      if (intervalRef.current) {
        clearInterval(intervalRef.current)
      }
    }
  }, [testimonials])

  return (
    <section className="py-20 bg-[#1a1a1a]">
      <div className="container px-6 mx-auto">
        <h2 className="text-4xl font-bold text-center text-[#9c88ff] mb-16">
          게이머들의 이야기
        </h2>
        <div className="overflow-hidden testimonial-container">
          <div className="flex testimonial-slider" ref={sliderRef}>
            {testimonials.map((testimonial, index) => (
              <div
                key={index}
                className="flex-shrink-0 w-1/3 px-2 testimonial-item"
              >
                <div className="h-full p-6 transition duration-300 bg-gray-800 bg-opacity-50 shadow-lg rounded-xl backdrop-blur-sm hover:bg-opacity-70">
                  <p className="text-[#b3b3b3] italic mb-4">
                    "{testimonial.quote}"
                  </p>
                  <p className="text-[#9c88ff] font-semibold">
                    {testimonial.author}
                  </p>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </section>
  )
}
