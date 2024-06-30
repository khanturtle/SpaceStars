// components/Footer.js
export default function Footer() {
  const teamMembers = ['구희영', '김가민', '김현진', '신다혜', '조준호']
  return (
    <footer className="bg-black bg-opacity-80 text-white py-8 footer">
      <div className="container mx-auto px-6">
        <div className="text-center mb-6">
          <h3 className="text-2xl font-bold mb-2 text-purple-300">우주별</h3>
          <p className="text-gray-300">우리 주변의 별별 사람들</p>
        </div>
        <div className="mb-6">
          <h4 className="text-lg font-semibold mb-3 text-center text-purple-200">
            꿈꾸는 별들
          </h4>
          <div className="flex justify-center flex-wrap">
            {teamMembers.map((member, index) => (
              <span key={index} className="mx-2 mb-2 text-gray-300">
                {member}
                {index < teamMembers.length - 1 ? '   •' : ''}
              </span>
            ))}
          </div>
        </div>
        <div className="text-center text-sm text-gray-400">
          <p>&copy; 2024 우주별. All rights reserved.</p>
        </div>
      </div>
    </footer>
  )
}
