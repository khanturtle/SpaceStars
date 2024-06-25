export default function NavHeader({
  title,
  description,
}: {
  title: string
  description: string
}) {
  return (
    <section className="bg-inherit fixed h-[100px] z-[2000] inline-flex flex-col items-start justify-end gap-2.5 px-[31px] py-[7px] top-0 nav-header">
      <h3 className="text-[#a589c7] text-4xl not-italic font-medium leading-[normal]">
        {title}
      </h3>
      <p className="text-[color:var(--White-50,#fff)] text-xs not-italic font-medium leading-[normal]">
        {description}
      </p>
    </section>
  )
}
