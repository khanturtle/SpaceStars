import styles from './navbar.module.css'

export default function NavHeader({
  title,
  description,
}: {
  title: string
  description: string
}) {
  return (
    // TODO: 위치 조정 필요 -> Nav랑 같이 수정
    <section
      className={`h-[100px] z-[1100] inline-flex flex-col items-start justify-end gap-2.5 px-[31px] py-[7px] ${styles['nav-header']}`}
    >
      <h3 className="text-[#a589c7] text-4xl not-italic font-medium leading-[normal]">
        {title}
      </h3>
      <p className="text-[color:var(--White-50,#fff)] text-xs not-italic font-medium leading-[normal]">
        {description}
      </p>
    </section>
  )
}
