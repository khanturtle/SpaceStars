'use client'

import TeamBox from './TeamBox'

export default function TeamListWrapper({
  searchParams,
  Teams,
}: {
  searchParams: { [key: string]: string }
  Teams: any[]
}) {
  const viewType = searchParams.view === 'list' ? 'list' : 'card'
  return (
    <TeamBox className="pt-[40px] flex">
      <TeamBox.TeamCardList items={Teams} type={viewType} />
    </TeamBox>
  )
}
