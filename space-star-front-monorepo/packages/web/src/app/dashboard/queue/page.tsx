import { getGames } from '@/apis/getGame'

import GameSelectBox from '@/containers/queue/GameSelectBox'

export default async function page() {
  const games = await getGames()

  return (
    <div className="relative flex items-center justify-center w-full h-full px-[16px]">
      <GameSelectBox games={games} />
    </div>
  )
}
