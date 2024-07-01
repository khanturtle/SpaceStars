'use client'

import Link from 'next/link'

const Hero = () => {
  return (
    <div className="pt-28 pb-20">
      <div className="container mx-auto px-6 flex flex-col md:flex-row items-center">
        <div className="mb-16">
          <h2 className="text-5xl font-bold mb-6 animate-fadeIn text-purple-300">
            우리 주변의 별별 사람들과 만나보세요
          </h2>
          <p className="text-xl mb-8 animate-fadeIn animation-delay-200 text-[color:var(--text-desc)]">
            우주별에서 당신의 게임 스타일에 맞는 완벽한 게임 친구를 만나보세요.
            함께 게임을 즐기고 우승을 향해 나아가세요!
          </p>
          <Link
            href="/sign-up"
            className="bg-purple-600 text-white font-bold py-3 px-8 rounded-full hover:bg-purple-500 transition duration-300 text-lg transform hover:scale-105"
          >
            게임 파티 찾기
          </Link>
        </div>
      </div>
    </div>
  )
}

const Features = () => {
  const features = [
    {
      title: '실시간 팀 채팅',
      description: '빠르고 효율적인 커뮤니케이션으로 팀의 생산성을 높이세요.',
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
    <div className="py-20 bg-purple-900 bg-opacity-50">
      <div className="container mx-auto px-6">
        <h2 className="text-4xl font-bold text-center text-purple-300 mb-16">
          우주별의 특별한 기능
        </h2>
        <div className="grid md:grid-cols-3 gap-12">
          {features.map((feature, index) => (
            <div
              key={index}
              className="bg-purple-800 bg-opacity-50 rounded-xl shadow-lg p-8 text-center transform hover:scale-105 transition duration-300"
            >
              <h3 className="text-2xl font-semibold mb-4 text-purple-200">
                {feature.title}
              </h3>
              <p className="text-gray-300">{feature.description}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}

export { Hero, Features }
