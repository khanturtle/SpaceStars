import DatePickerCustom from '@/components/DatePicker/DatePicker'
import TeamBox from '@/containers/team-list/TeamBox'

export default function page() {
  return (
    <div>
      <div>
        <h1>데이트피커</h1>
        <DatePickerCustom id="" />
      </div>

      <div>
        <h1>팀원찾기</h1>
        <TeamBox>ㅇㅅㅇ</TeamBox>
      </div>
    </div>
  )
}
