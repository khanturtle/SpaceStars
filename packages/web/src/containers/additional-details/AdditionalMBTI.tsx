import { Select } from '@packages/ui'

export default function AdditionalMBTI({
  mbti,
  onChange,
}: {
  mbti: string
  onChange: (value: string) => void
}) {
  const MBTIOptions = [
    { value: 'ISTJ', label: 'ISTJ' },
    { value: 'ISFJ', label: 'ISFJ' },
    { value: 'INFJ', label: 'INFJ' },
    { value: 'INTJ', label: 'INTJ' },
    { value: 'ISTP', label: 'ISTP' },
    { value: 'ISFP', label: 'ISFP' },
    { value: 'INFP', label: 'INFP' },
    { value: 'INTP', label: 'INTP' },
    { value: 'ESTP', label: 'ESTP' },
    { value: 'ESFP', label: 'ESFP' },
    { value: 'ENFP', label: 'ENFP' },
    { value: 'ENTP', label: 'ENTP' },
    { value: 'ESTJ', label: 'ESTJ' },
    { value: 'ESFJ', label: 'ESFJ' },
    { value: 'ENFJ', label: 'ENFJ' },
    { value: 'ENTJ', label: 'ENTJ' },
  ]

  return (
    <section className="w-full mb-10">
      <Select
        className="w-full"
        id="MBTI"
        options={MBTIOptions}
        selectedOption={mbti}
        onChange={onChange}
      />
    </section>
  )
}
