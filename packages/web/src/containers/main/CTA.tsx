import Link from 'next/link'

export default function CTA() {
  return (
    <section className="py-20 bg-gradient-to-r from-purple-800 to-blue-800">
      <div className="container px-6 mx-auto text-center">
        <h2 className="text-4xl font-bold mb-8 text-[#9c88ff]">
          당신의 게임 우주에서 새로운 별을 찾아보세요
        </h2>
        <p className="mb-12 text-xl text-gray-300">
          지금 바로 우주별에서 새로운 게임 친구를 만나보세요. 함께하면 게임이
          더욱 즐거워집니다!
        </p>
        <Link
          href="/sign-up"
          className="bg-[#9c88ff] text-white font-bold py-3 px-8 rounded-full hover:bg-purple-600 transition duration-300 text-lg transform hover:scale-105"
        >
          시작하기
        </Link>
      </div>
    </section>
  )
}
