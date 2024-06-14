import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'

export default function AdditionalMBTI({
  mbti,
  onChange,
}: {
  mbti: string
  onChange: (value: string) => void
}) {
  const MBTIOptions = [
    { id: 1, value: 'ISTJ', label: 'ISTJ' },
    { id: 2, value: 'ISFJ', label: 'ISFJ' },
    { id: 3, value: 'INFJ', label: 'INFJ' },
    { id: 4, value: 'INTJ', label: 'INTJ' },
    { id: 5, value: 'ISTP', label: 'ISTP' },
    { id: 6, value: 'ISFP', label: 'ISFP' },
    { id: 7, value: 'INFP', label: 'INFP' },
    { id: 8, value: 'INTP', label: 'INTP' },
    { id: 9, value: 'ESTP', label: 'ESTP' },
    { id: 10, value: 'ESFP', label: 'ESFP' },
    { id: 11, value: 'ENFP', label: 'ENFP' },
    { id: 12, value: 'ENTP', label: 'ENTP' },
    { id: 13, value: 'ESTJ', label: 'ESTJ' },
    { id: 14, value: 'ESFJ', label: 'ESFJ' },
    { id: 15, value: 'ENFJ', label: 'ENFJ' },
    { id: 16, value: 'ENTJ', label: 'ENTJ' },
  ]

  // TODO: 뒷 배경 찾기 ㅠㅠ
  return (
    <Select>
      <SelectTrigger
        className=" w-full h-[60px] mb-10  border border-[color:var(--White-50,#fff)] shadow-[0px_4px_10px_0px_rgba(37,73,150,0.1)] rounded-[10px] border-solid text-[color:var(--secondary-text-color,#666)] text-base not-italic font-normal leading-[170%] "
        style={{ background: 'rgba(255, 255, 255, 0.5)' }}
      >
        <SelectValue placeholder="MBTI" />
      </SelectTrigger>
      <SelectContent className="z-[9999] w-full h-full bg-[white] border-none ">
        {MBTIOptions.map((item) => (
          <SelectItem
            key={item.id}
            value={item.value}
            className="text-base not-italic font-normal leading-[170%] bg-[none] m-0"
          >
            {item.value}
          </SelectItem>
        ))}
      </SelectContent>
    </Select>
  )
}
