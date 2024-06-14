import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'
import TeamBox from '@/containers/team-list/TeamBox'

export default function page() {
  return (
    <div>
      <div>
        <h1>팀원찾기</h1>
        <TeamBox>ㅇㅅㅇ</TeamBox>
      </div>

      <Select>
        <SelectTrigger
          className="relative w-[300px] h-[60px] mb-10  border border-[color:var(--White-50,#fff)] shadow-[0px_4px_10px_0px_rgba(37,73,150,0.1)] rounded-[10px] border-solid text-[color:var(--secondary-text-color,#666)] text-base not-italic font-normal leading-[170%]"
          style={{ background: 'rgba(255, 255, 255, 0.5)' }}
        >
          <SelectValue placeholder="MBTI" />
        </SelectTrigger>
        <SelectContent className="z-[9999] w-full h-full bg-[white] border-none">
          <SelectItem
            value="1"
            className="text-base not-italic font-normal leading-[170%] bg-[none] m-0"
          >
            1
          </SelectItem>
        </SelectContent>
      </Select>
    </div>
  )
}
