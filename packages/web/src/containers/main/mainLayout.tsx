'use client'

import Link from 'next/link'

const Hero = () => {
  return (
    <section className="pb-20 text-white pt-28 bg-gradient-to-r from-blue-600 to-purple-600">
      <div className="container px-6 mx-auto text-center">
        <h2 className="mb-4 text-5xl font-bold">
          우리 주변의 별별 사람들과 만나보세요
        </h2>
        <p className="mb-8 text-xl">
          우주별에서 당신의 게임 스타일에 맞는 완벽한 게임 친구를 만나보세요.
          친구를 만들고, 채팅하고, 게임을 즐기세요!
        </p>
        <Link
          href="/sign-up"
          className="px-8 py-3 font-bold text-blue-600 transition duration-300 bg-white rounded-full hover:bg-blue-100"
        >
          시작하기
        </Link>
      </div>
    </section>
  )
}

const Features = () => {
  const features = [
    {
      title: '실시간 팀 채팅',
      description: '다른 게이머들과 실시간으로 대화를 나눌 수 있습니다.',
    },
    {
      title: 'AI 기반 친구 추천',
      description: '관심사와 스타일을 기반으로 최적의 게임 친구를 만나보세요.',
    },
    {
      title: '실시간 매칭',
      description: '적합한 팀원을 즉시 찾아 게임을 시작하세요.',
    },
  ]

  return (
    <section id="features" className="py-20 bg-gray-800">
      <div className="container px-6 mx-auto">
        <h2 className="mb-8 text-3xl font-bold text-center text-[#9c88ff]">
          주요 기능
        </h2>
        <div className="grid gap-12 md:grid-cols-3">
          {features.map((feature, index) => (
            <div
              key={index}
              className="p-8 text-center transition duration-300 transform bg-gray-700 bg-opacity-50 shadow-lg rounded-xl hover:scale-105"
            >
              <h3 className="mb-4 text-2xl font-semibold text-[#9c88ff]">
                {feature.title}
              </h3>
              <p className="text-[#b3b3b3]">{feature.description}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  )
}

export { Hero, Features }
